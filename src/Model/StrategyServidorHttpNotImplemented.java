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
public class StrategyServidorHttpNotImplemented extends StrategyServidorHttp {

    @Override
    public void processaRequisicao(HeaderNavegador header) {
        this.resposta = new RespostaHttp();
        this.resposta.setNotImplemented();
    }

    @Override
    public byte[] getCabecalhoRespRequisicao() {
        System.out.println(new String(resposta.getCabecalho(),0,resposta.getCabecalho().length));
        return this.resposta.getCabecalho();
    }

    @Override
    public byte[] getConteudoRespRequisicao() {
        return this.resposta.getConteudo();
    }
    
    
    
}
