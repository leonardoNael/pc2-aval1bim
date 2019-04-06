package br.com.etechoracio.avaliacao.view;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.etechoracio.avaliacao.business.DisciplinaSB;
import br.com.etechoracio.avaliacao.business.ProfessorSB;
import br.com.etechoracio.avaliacao.model.Disciplina;
import br.com.etechoracio.avaliacao.model.Professor;
import br.com.etechoracio.common.view.BaseMB;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Controller
@Scope("view")
public class DisciplinaMB extends BaseMB {

	@Autowired
	private DisciplinaSB disciplinaSB;
	
	@Autowired
	private ProfessorSB professorSB;

	private Disciplina edit = new Disciplina();
	private DualListModel<Professor> professores;

	public void postConstruct() {
		List<Professor> source = professorSB.findAll();
		professores = new DualListModel<Professor>(source, new ArrayList<Professor>());

	}

	public void onSave() {
		edit.setProfessores(professores.getTarget());
		
		if(professores.getTarget().size() > 2) {
			showErrorMessage("É permitido apenas dois professores por\r\n" + 
					"disciplina.");
		}
		else {
			disciplinaSB.save(edit);
			showInsertMessage();
		}
	}
}
