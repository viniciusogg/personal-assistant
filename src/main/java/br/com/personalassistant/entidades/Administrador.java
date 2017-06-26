package br.com.personalassistant.entidades;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@DiscriminatorValue("Admin")
@Table(name = "TB_ADMINISTRADOR")
@Entity(name = "Administrador")
public class Administrador extends Usuario{

	private static final long serialVersionUID = 1L;
	
}
