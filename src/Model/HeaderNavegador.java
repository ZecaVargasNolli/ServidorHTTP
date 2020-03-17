/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Giancarlo
 */
public class HeaderNavegador {
    
    private String metodo;
    private String caminho;
    private String versaoHttp;
    private String conteudo;

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho.trim();
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getVersaoHttp() {
        return versaoHttp;
    }

    public void setVersaoHttp(String versaoHttp) {
        this.versaoHttp = versaoHttp;
    }

    @Override
    public String toString() {
        return "HeaderNavegador{" + "metodo=" + metodo + ", caminho=" + caminho + ", versaoHttp=" + versaoHttp + ", conteudo=" + conteudo + '}';
    }

    

}
