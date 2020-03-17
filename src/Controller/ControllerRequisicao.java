package Controller;

import Model.FactoryStrategyServidorHttp;
import Model.StrategyServidorHttp;
import Model.HeaderNavegador;
import Model.LoaderHeaderNavegador;
import Model.RespostaHttp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;


/**
 *
 * @author Giancarlo
 */
public class ControllerRequisicao {

    private Socket socket;
    private HeaderNavegador header; 
    private StrategyServidorHttp servHttp;

    public void processaRequisicao() {
        try {
            this.header = LoaderHeaderNavegador.load(this.lerRequisicao());
            this.servHttp = FactoryStrategyServidorHttp.getStrategyServidorHttp(this.header.getMetodo());
            this.servHttp.processaRequisicao(this.header);
            this.escreverRespostaNavegador(this.servHttp.getCabecalhoRespRequisicao());
            this.escreverRespostaNavegador(this.servHttp.getConteudoRespRequisicao());
        }
        catch (Exception e) {
            RespostaHttp resp = new RespostaHttp();
            resp.setServiceUnavaliable();
            this.escreverRespostaNavegador(resp.getCabecalho());
            this.escreverRespostaNavegador(resp.getConteudo());
        }
        
    }
    
    private void escreverRespostaNavegador(byte[] resposta) {
        try {
            OutputStream outStr = this.socket.getOutputStream();
            outStr.write(resposta);
        } 
        catch (IOException ex) {

        }
    }
    
    private String lerRequisicao() {
        String requisicaoRecebida = "";
        try{
            int tamanhoConteudo = 0;
            String linha = "";
            BufferedReader input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            linha = input.readLine();
            requisicaoRecebida = linha + "\r\n";
            while(!linha.isEmpty()){
                if(linha.split(" ")[0].equals("Content-Length:")) {
                    tamanhoConteudo = Integer.parseInt(linha.split(" ")[1]);
                }
                linha = input.readLine();
                requisicaoRecebida += linha + "\r\n";
            }
            /* se possuir conteudo (POST) */
            String conteudo = "";
            if(tamanhoConteudo > 0){
                for (int i = 0; i < tamanhoConteudo; i++) {
                    conteudo += (char) input.read();
                }
            }
            requisicaoRecebida += conteudo;
        }
        catch(Exception ex){
            
        }
        
        return requisicaoRecebida;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

}
