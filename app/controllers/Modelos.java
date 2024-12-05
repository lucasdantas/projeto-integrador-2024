package controllers;

import java.util.Date;
import java.util.List;

import models.Modelo;
import play.mvc.Controller;

public class Modelos extends Controller {

	public static void form(String grupo) {
		if (grupo == null || grupo.trim().isEmpty()) {
			badRequest("É necessário informar o parâmetro grupo");
		}
		renderTemplate("Modelos/" + grupo + ".html", grupo);
	}

	public static void listar(String grupo) {
		if (grupo == null || grupo.trim().isEmpty()) {
			badRequest("É necessário informar o parâmetro grupo");
		}

		List<Modelo> modelos = Modelo.find("grupo = ?1", grupo.toLowerCase()).fetch();
		render(modelos, grupo);
	}

	public static void salvar(String grupo, String nome, String descricao,
			Double numero, String tipo, List<String> opcoes, String data) {

		Modelo modelo = new Modelo();
		modelo.grupo = grupo.toLowerCase();
		modelo.nome = nome;
		modelo.descricao = descricao;
		modelo.numero = numero;
		modelo.tipo = tipo;
		modelo.opcoes = opcoes;
		modelo.data = data;
		modelo.cadastradoEm = new Date();

		validation.valid(modelo);
		if (validation.hasErrors()) {
			flash.keep();
			renderTemplate("Modelos/" + grupo + ".html", grupo, modelo);
		}

		modelo.save();

		flash.success("Cadastro realizado com sucesso. Parabéns, " + grupo + "!");
		renderTemplate("Modelos/sucesso.html", modelo);
	}


	public static void remover(Long id, String grupo) {
		if (grupo == null || grupo.trim().isEmpty()) {
			badRequest("É necessário informar o parâmetro grupo");
		}
		if (id == null) {
			badRequest("É necessário informar o id do modelo para remoção");
		}

		Modelo modelo = Modelo.findById(id);
		if (modelo == null) {
			flash.error("Modelo não encontrado. O Registro não pôde ser removido!");
			listar(grupo);
		}
		modelo.delete();
		flash.success("O registro foi removido com sucesso.");
		listar(grupo);
	}

	public static void cadastros() {
		List<Modelo> modelos = Modelo.find("order by cadastradoEm desc").fetch();
		render(modelos);
	}
}
