package Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Jose Vargas Nolli
 */
public class StrategyServidorHttpPOST extends StrategyServidorHttp {

    private File arquivoReq;

    @Override
    public void processaRequisicao(HeaderNavegador header) {
        this.resposta = new RespostaHttp();
        this.arquivoReq = getFileReqGet(header.getCaminho());
        try {
            this.processaSalvamentoEmArquivo(this.arquivoReq, header);
        } 
        catch (Exception ex) {}
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

    private File getFileReqGet(String caminho) {
        caminho = "public_html" + caminho;
        /* garantindo que sempre vai ser buscado na pasta public_html */
        File arq = new File(caminho);
        if (!arq.exists()) {
            if (arq.getName().equals("teapot")) {
                return arq;/* enviando arquivo porcausa do erro 418 que sera verificado posteriormente */
            }
            /* not found */
            return null;
        }

        if (!arq.isDirectory()) {
            return null;
        }
        return arq;
    }

    private File verificaExistenciaArquivo(File arqInicial) {
        File arq = new File(arqInicial.getPath());
        if (arq.exists()) {
            return arq;
        }
        return arqInicial;
    }

    private void setInfo() {
        if (this.arquivoReq == null) {
            this.resposta.setBadRequest400();
            return;
        }
        if (this.arquivoReq.getName().equals("teapot")) {
            this.resposta.setImTeapot();
            return;
        }
        if (this.verificaExistenciaArquivo(this.arquivoReq) == null) {
            this.resposta.setBadRequest400();
            return;
        } 
        
        this.resposta.setStatusSucessPost();
       
    }

    private void processaSalvamentoEmArquivo(File arqInicial, HeaderNavegador header) throws IOException {
        File arq = new File(arqInicial.getPath());
        if (arq.exists()) {
            BufferedWriter write = new BufferedWriter(new FileWriter(arqInicial.getPath() + "/"+arqInicial.getName()+".txt", true));
            String Conteudos[];
            String valoresSalvos[];
            String conteudo = header.getConteudo();
            Conteudos = conteudo.split("&");
            conteudo = "";
            int contador = 0;
            while (true) {
               if(contador >= Conteudos.length) {
                   break;
               }
                valoresSalvos = Conteudos[contador].split("=");
                conteudo += valoresSalvos[1] + ";";
                contador++;
            }
            write.write(conteudo + "\n");
            write.close();
        }
    }
}
