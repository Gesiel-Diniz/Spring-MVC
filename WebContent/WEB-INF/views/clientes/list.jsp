<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div class="row">
	<div class="col-md-8">
		<h2>Clientes  <button type="button" class="btn btn-outline-success btn-sm" onclick="$('.modal').css('display','block')">Add+</button></h2>
	</div>
	
	<div class="col-md-4">
		<div class="form-group">
			<div class="input-group mb-3">
				<input class="form-control" id="pesquisar" aria-label="Amount (to the nearest dollar)" type="text" placeholder="Pesquise pelo nome" />
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
			<th>NOME</th>
			<th>TELEFONE</th>
			<th>AÇÕES</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${!empty listClientes}">
			<c:forEach items="${listClientes}" var="cliente">
				<tr>
					<td>${cliente.id}</td>
					<td>${cliente.nome}</td>
					<td>${cliente.telefone}</td>
					<td>
						<div class="badge badge-secondary" style="cursor: pointer;" onclick="getEdt(${cliente.id})">Alterar</div> 
						<div class="badge badge-danger" style="cursor: pointer;" onclick="excluir(${cliente.id})">Excluir</div>
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
			
				<c:url var="actionAdd" value="/clientes/add" />
	
				<form:form action="${actionAdd}" method="post" modelAttribute="cliente" id="form">
					
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								Nome<br/>
								<form:input path="nome" class="form-control" placeholder="" id="nome" type="text" />
								<div class="text-danger" style="font-size: 11px;"><form:errors path="nome"></form:errors></div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								Telefone<br/>
								<form:input path="telefone" class="form-control" placeholder="" id="telefone" type="text" />
								<div class="text-danger" style="font-size: 11px;"><form:errors path="telefone"></form:errors></div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								E-mail<br/>
								<form:input path="email" class="form-control" placeholder="" id="email" type="text" />
								<div class="text-danger" style="font-size: 11px;"><form:errors path="email"></form:errors></div>
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

			window.location.href="/clinica/clientes/del/"+id;

		}
		
	}


	function pesquisar(){

		$.ajax({
			method: 'GET',
			url: '/clinica/clientes/nome?nome='+$("#pesquisar").val(),
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
		$("#form").attr("action","/clinica/clientes/add");

		if(data){
			
			$.each(data[0], function(index, value){

				$("#"+index).val("");
				
			});
			
		}
		
	}


	function getEdt(id){

		$.ajax({
			method: 'GET',
			url: '/clinica/clientes/getEdt?id='+id,
			success: function(retorno){

				console.log(retorno);
				
				data = retorno;

				$("#form").attr("action","/clinica/clientes/edt")
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