<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="row">
	<div class="col-md-8">
		<h2>Prontuários  <button type="button" class="btn btn-outline-success btn-sm" onclick="$('.modal').css('display','block')">Add+</button></h2>
	</div>
	
	<div class="col-md-4">
		<div class="form-group">
			<div class="input-group mb-3">
				<input class="form-control" id="pesquisar" aria-label="Amount (to the nearest dollar)" type="text" placeholder="Pesquise pela observação" />
				<div class="input-group-append">
					<span style="cursor: pointer;" onclick="pesquisar()" class="btn btn-secondary">Pesquisar</span>
				</div>
			</div>
		</div>
	</div>
				
</div>

<table id="tbl-list" class="table table-hover table-striped table-bordered">
	<thead>
		<tr>
			<th>ID</th>
			<th>OBSERVAÇÃO</th>
			<th>DATA DE ATENDIMENTO</th>
			<th>AÇÕES</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${!empty listProntuarios}">
			<c:forEach items="${listProntuarios}" var="prontuario">
				<tr>
					<td>${prontuario.id}</td>
					<td>${prontuario.observacoes}</td>
					<td><fmt:formatDate value="${prontuario.dataAtendimento}" pattern="dd/MM/yyyy" timeZone="UTC" /></td>
					<td>
						<div class="badge badge-secondary" style="cursor: pointer;" onclick="getEdt(${prontuario.id})">Alterar</div> 
						<div class="badge badge-danger" style="cursor: pointer;" onclick="excluir(${prontuario.id})">Excluir</div>
					</td>
				</tr>
			</c:forEach>
		</c:if>
	</tbody>
</table>




<!-- Form ADD e EDT  -->
<div class="modal" >
	<div class="modal-dialog" role="document">
		<div class="modal-content" >
			<div class="modal-header">
				<h5 class="modal-title">Novo cliente</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="fechar()">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
			
				<c:url var="actionAdd" value="/prontuarios/add" />
	
				<form:form action="${actionAdd}" method="post" modelAttribute="prontuario" id="form">
					
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								Médico<br/>
								<form:select path="medico.id" cssClass="form-control" id="id_medico" >
									<option value="">Selecione</option>
									<form:options items="${medicos}" itemValue="id" itemLabel="nome" />
								</form:select>
								<div class="text-danger" style="font-size: 11px;"><form:errors path="medico.id"></form:errors></div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								Animal<br/>
								<form:select path="animal.id" cssClass="form-control" id="id_animal" >
									<option value="">Selecione</option>
									<form:options items="${animais}" itemValue="id" itemLabel="nome" />
								</form:select>
								<div class="text-danger" style="font-size: 11px;"><form:errors path="animal.id"></form:errors></div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								Data do atendimanto<br/>
								<form:input path="dataAtendimento" class="form-control" placeholder="" id="dataAtendimento" type="text" />
								<div class="text-danger" style="font-size: 11px;"><form:errors path="dataAtendimento"></form:errors></div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								Observações<br/>
								<form:textarea path="observacoes" class="form-control" placeholder="" id="observacoes" />
								<div class="text-danger" style="font-size: 11px;"><form:errors path="observacoes"></form:errors></div>
							</div>
						</div>
					</div>
					
					<form:hidden path="id" id="id"/>
				</form:form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" onclick="$('form').submit()">Salvar</button>
				<button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="fechar()">Fechar</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">

	var data = null;


	function excluir(id){

		if(confirm("Deseja realmente excluir?")){

			window.location.href="/clinica/prontuarios/del/"+id;

		}
		
	}


	function pesquisar(){

		$.ajax({
			method: 'GET',
			url: '/clinica/prontuarios/nome?nome='+$("#pesquisar").val(),
			success: function(data){
				
				$("#tbl-list tbody tr").remove();
				
				$.each(data, function(index, value){
				
					$("#tbl-list tbody").append('<tr>'+
													'<td>'+value.id+'</td>'+
													'<td>'+value.nome+'</td>'+
													'<td>'+value.telefone+'</td>'+
													'<td>'+
														'<div class="badge badge-secondary" style="cursor: pointer;" onclick="getEdt('+value.id+')">Alterar</div> '+
														'<div class="badge badge-danger" style="cursor: pointer;" onclick="excluir('+value.id+')">Excluir</div>'+
													'</td>'+
													'</tr>');
	
					});
				
			},
			error: function(){
				alert("Houve um erro na requisição.");
			}
			
		});

	}
	

	function fechar(){
		
		$('.modal').css('display','none');
		$("#form").attr("action","/prontuarios/add");

		if(data){
			
			$.each(data[0], function(index, value){

				$("#"+index).val("");
				
			});
			
		}
		
	}


	function getEdt(id){

		$.ajax({
			method: 'GET',
			url: '/clinica/prontuarios/getEdt?id='+id,
			success: function(retorno){

				console.log(retorno);
				
				data = retorno;

				$("#form").attr("action","/prontuarios/edt")
				$('.modal').css('display','block');
				
				$.each(data[0], function(index, value){

					$("#"+index).val(value);
					
				});
				
			},
			error: function(retorno){
				console.log(retorno);
				alert("Houve um erro na requisição.");
			}
			
		});

	}

</script>