package br.com.etechoracio.avaliacao.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import br.com.etechoracio.common.model.BaseORM;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "TBL_DISCIPLINA")
public class Disciplina extends BaseORM {

	@Id
	@GeneratedValue
	@Column(name = "ID_DISCIPLINA")
	private Long id;

	@Column(name = "TX_NOME")
	private String nome;

	@Column(name = "NR_AULAS_SEMANAIS")
	private Double aulas;

	@Column(name = "DT_CRIACAO")
	private Date dtCriacao;

	@ManyToMany
	@JoinTable(name = "TBL_REL_DISCIPLINA_PROFESSOR", joinColumns = {
			@JoinColumn(name = "ID_DISCIPLINA") }, inverseJoinColumns = { @JoinColumn(name = "ID_PROFESSOR") })

	private List<Professor> professores;

	@PrePersist
	private void preencerDataCriacao() {
		if (dtCriacao == null) {
			dtCriacao = new Date();
		}
	}

}
