package models;

import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Modelo extends Model {

	@Required
	public String grupo;

	@Required
	public String nome;
	public String descricao;

	public Double numero;

	public String tipo;

	@ElementCollection
	public List<String> opcoes;

	//@Temporal(TemporalType.DATE)
	public String data;

	@Temporal(TemporalType.DATE)
	public Date cadastradoEm;

}
