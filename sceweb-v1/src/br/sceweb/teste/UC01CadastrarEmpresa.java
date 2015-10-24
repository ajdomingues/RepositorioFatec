package br.sceweb.teste;
import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.sceweb.modelo.Empresa;
import br.sceweb.modelo.EmpresaDAO;
import br.sceweb.modelo.Controle;

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
		assertEquals(1, empresaDAO.adiciona(empresa));
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
		Empresa empresa2 = new Empresa();
		try{
			empresa2.setNomeDaEmpresa("");

		}catch(Exception e){
			assertEquals("nome da empresa inv�lido!", e.getMessage());
		}
		try{
			empresa2.setEndereco("");

		}catch(Exception e){
			assertEquals("endere�o inv�lido!", e.getMessage());
		}

		try{
			empresa2.setNomeFantasia("");

		}catch(Exception e){
			assertEquals("nome fantasia inv�lido!", e.getMessage());
		}
		try{
			empresa2.setTelefone("");

		}catch(Exception e){
			assertEquals("telefone inv�lido!", e.getMessage());
		}

		try{
			empresa2.setCnpj("");

		}catch(Exception e){
			assertEquals("cnpj inv�lido!", e.getMessage());
		}
	}
@Test
public void CT05UC02Cadastrar_empresa_com_sucesso(){
	
}
	@AfterClass
	/**
	 * obj - exclui o cnpj ao finalizar o teste*/
	public static void tearDownAfterClass() throws Exception {
		empresaDAO.exclui("89424232000180");
	}



}
