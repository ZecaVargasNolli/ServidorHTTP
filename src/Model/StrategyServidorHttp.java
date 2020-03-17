/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.HeaderNavegador;

/**
 *
 * @author Giancarlo
 */
public abstract class StrategyServidorHttp {
        
    protected RespostaHttp resposta;
    
    public abstract void processaRequisicao(HeaderNavegador header);
    public abstract byte[] getCabecalhoRespRequisicao();
    public abstract byte[] getConteudoRespRequisicao();
  
}
