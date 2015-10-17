package br.sceweb.teste;
import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.sceweb.modelo.Empresa;
import br.sceweb.modelo.EmpresaDAO;


public class UC01CadastrarEmpresa {
	static EmpresaDAO empresaDAO;
	static Empresa empresa;
	@BeforeClass
	/**
	 * cria os obejtos que realizam o caso de uso cadastrar empresa*/
	public static void setUpBeforeClass() throws Exception {
		empresaDAO = new EmpresaDAO();
		empresa = new Empresa();
		//89.424.232/0001-80
		empresa.setCnpj("89424232000180");
		empresa.setNomeDaEmpresa("empresa x");
		empresa.setNomeFantasia("empresa x");
		empresa.setEndereco("Rua taquari");
		empresa.setTelefone("2222");
	}
	/**
	 * obj - verificar o comportamento do sistema na inclus�o de uma empresa com sucesso
	 * @throws SQLException 
	 * */


	@Test
	public void CT01UC01FBCadastra_empresa_com_sucesso() throws SQLException {
		empresaDAO.exclui("89424232000180");
		assertEquals("cadastro realizado com sucesso", empresaDAO.adiciona(empresa));
		empresaDAO.exclui("89424232000180");
	}
	/**
	 * obj - verificar o comportamento do sistema no cadastro com cnpj ja cadastrado
	 * @throws SQLException */
	@Test(expected = RuntimeException.class)
	public void CT02UC01A2Cadastra_empresa_cnpj_ja_cadastrado() throws SQLException{
		empresaDAO.adiciona(empresa);
		assertEquals(0, empresaDAO.adiciona(empresa));
	}
	/**
	 * obj - verificar o comportamento do sistema no cadastro com cnpj inv�lido*/
	@Test
	public void CT03UC01A3Cadastrar_empresa_cnpj_invalido(){
		Empresa empresa2 = new Empresa();
		try{
			empresa2.setCnpj("8942423200018");
			fail("deveria disparar uma exception");
		}catch (Exception e){
			assertEquals("CNPJ inv�lido!",e.getMessage());
		}
	}
	/**
	 * obj - verificar o comportamento do sistema no cadastro de uma empresa com dados invalidos*/
	public void CT04UC01A4Cadastra_com_dados_invalidos(){

	}
	@AfterClass
	/**
	 * obj - exclui o cnpj ao finalizar o teste*/
	public static void tearDownAfterClass() throws Exception {
		//empresaDAO = new EmpresaDAO();
		//empresaDAO.exclui("89424232000180");
	}



}
