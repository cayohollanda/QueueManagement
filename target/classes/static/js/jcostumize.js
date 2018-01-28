
$('#exclusaoUsuario').on('show.bs.modal',function (event){
	
	var button = $(event.relatedTarget);
	var idUsuario = button.data('id');
	var descricao = button.data('nome');
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	if (!action.endsWith('/')) {
		action += '/';
	}
	form.attr('action', action + idUsuario);
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir o usuário <strong>' + descricao + '</strong>?');
});

$('#exclusaoNota').on('show.bs.modal',function (event){
	
	var button = $(event.relatedTarget);
	var idNota = button.data('id');
	var valor = button.data('nome');
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	if (!action.endsWith('/')) {
		action += '/';
	}
	form.attr('action', action + idNota);
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir essa nota <strong>' + valor + '</strong>?');
});

$('#exclusaoAluno').on('show.bs.modal',function (event){
	
	var button = $(event.relatedTarget);
	var idAluno = button.data('id');
	var nome = button.data('nome');
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	if (!action.endsWith('/')) {
		action += '/';
	}
	form.attr('action', action + idAluno);
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir esse aluno <strong>' + nome + '</strong>?');
});


$('#exclusaoTurma').on('show.bs.modal',function (event){
	
	var button = $(event.relatedTarget);
	var idTurma = button.data('id');
	var turma = button.data('nome');
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	if (!action.endsWith('/')) {
		action += '/';
	}
	form.attr('action', action + idTurma);
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir essa turma <strong>' + turma + '</strong>?');
});




$(function() {
	$('.js-currency').maskMoney({
		decimal:',', thousand:'.', allawZero:true
	});
	
	$('.js-atualiza-status').on('click', function(event) {
		
		event.preventDefault();
		var botaoAtualizarStatus = $(event.currentTarget);
		var urlAtualizar = botaoAtualizarStatus.attr('href');
		
		var response = $.ajax({
			url:urlAtualizar,
			type:'PUT'
		});
		
		
		response.done(function(e) {
			var idUsuario = urlAtualizar.data('id');
			$('[data-role=' + idUsuario + ']').html('<span class="label label-success">' + e + '</span>');
		});
		
		response.fail(function(e) {
			console.log(e);
			alert('Erro recebendo cobrança');
		});
		
		
	});
});
