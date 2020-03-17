/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.File;
import java.io.FileInputStream;
import javax.activation.MimetypesFileTypeMap;

/**
 *
 * @author Giancarlo
 */
public class StrategyServidorHttpGET extends StrategyServidorHttp {
    
    private File arquivoReq;
       
    @Override
    public void processaRequisicao(HeaderNavegador header) {
        this.resposta = new RespostaHttp();
        this.arquivoReq = getFileReqGet(header.getCaminho());
        this.setInfo();
    }

    @Override
    public byte[] getCabecalhoRespRequisicao() {
        return this.resposta.getCabecalho();
      
    }

    @Override
    public byte[] getConteudoRespRequisicao() { 
        return this.resposta.getConteudo();
    }
    
    private byte[] getBytesArquivoDir() {
        String respostaHtml = "<html>\n" +
                              "    <head>\n" +
                              "        <title>TODO supply a title</title>\n" +
                              "        <meta charset=\"windows-1252\">\n" +
                              "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                              "    </head>\n" +
                              "    <body>\n" +
                              "        <div>Arquivos do servidor</div>\n" +
                              getTreeViewDiretorio(this.arquivoReq, "")+ "\n" +
                              "    </body>\n" +
                              "</html>";
        return respostaHtml.getBytes();
    }
    
    private String getTreeViewDiretorio(File arq, String espaco) {
        String resposta =  espaco + "<a href=\""+ arq.getPath().replaceAll("public_html" , "") +"\">" + arq.getName() + "</a><br>\n";
        if (arq.isDirectory()) {
            File[] arquivos = arq.listFiles();
            for (File arquivo : arquivos) {
                resposta +=  getTreeViewDiretorio(arquivo, espaco + "&nbsp&nbsp&nbsp&nbsp&nbsp");
            }
            
        }        
        return resposta;
    }
    
    private byte[] getBytesArquivo() {
        byte[] byteArq; 
        try {
            FileInputStream leitorArquivo = new FileInputStream(this.arquivoReq);
            byteArq = new byte[leitorArquivo.available()];
            leitorArquivo.read(byteArq);
        } 
        catch (Exception ex) {
            byteArq = new byte[0];
        } 
        
        return byteArq;
    }
    
    private File getFileReqGet(String caminho) {
        caminho = "public_html" + caminho; /* garantindo que sempre vai ser buscado na pasta public_html */
        File arq = new File(caminho);
        if (!arq.exists()) {
            if(arq.getName().equals("teapot")){
                return arq;/* enviando arquivo porcausa do erro 418 que sera verificado posteriormente */
            }
            /* not found */
            return null; 
        }
        
        if(arq.isDirectory()){
           /*procura index.html (se nao achar mantem o diretorio)*/
           arq = this.verificaIndexHtml(arq);
        }
        return arq;
    }

    private File verificaIndexHtml(File arqInicial) {
        File arq = new File(arqInicial.getPath() + "\\index.html");
        if (arq.exists()) {
            return arq; 
        }
        return arqInicial; 
    }
    
    private byte[] processaConteudoResp(){
        if(this.arquivoReq.isDirectory()){
            return getBytesArquivoDir();
        }
        else {
            return getBytesArquivo();
        }
    }
    
    private void setInfo() {
        if (this.arquivoReq == null) {
            this.resposta.setNotFound404();
            return;
        }
        if (this.arquivoReq.getName().endsWith(".inc")) {
            this.resposta.setForbidden403();
            return;
        }
        if (this.arquivoReq.getName().equals("teapot")) {
            this.resposta.setImTeapot();
            return;
        }
        this.resposta.setConteudoResp(processaConteudoResp());
        this.resposta.setMimeTypeArq(this.arquivoReq.isDirectory()? "text/html" : new MimetypesFileTypeMap().getContentType(this.arquivoReq));     
    }
    
}
