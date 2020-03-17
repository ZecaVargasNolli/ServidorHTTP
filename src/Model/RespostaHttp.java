package Model;

/**
 *
 * @author Giancarlo
 */
public class RespostaHttp {

    private String mimeTypeArq;
    private String codStatusResp;
    private byte[] conteudoResp;
    private byte[] cabecalhoResp;

    public RespostaHttp() {
        this.codStatusResp = "200 OK";
        this.cabecalhoResp = null;
        this.conteudoResp = null;
        this.mimeTypeArq = "";
        this.codStatusResp = "";
    }

    public byte[] getConteudo() {

        return this.conteudoResp;
    }

    public byte[] getCabecalho() {
        if (this.cabecalhoResp == null) {
            setCabecalhoPadrao();
        }
        return this.cabecalhoResp;
    }

    private void setCabecalhoPadrao() {
        String resposta = "";
        resposta += "HTTP/1.1 " + this.codStatusResp + "\n";
        resposta += "Content-type: " + this.mimeTypeArq + "\n";
        resposta += "Content-Length: " + this.conteudoResp.length + "\n";
        resposta += "\r\n";
        this.cabecalhoResp = resposta.getBytes();
    }

    public void setMimeTypeArq(String mimeTypeArq) {
        this.mimeTypeArq = mimeTypeArq;
    }

    public void setCodStatusResp(String codStatusResp) {
        this.codStatusResp = codStatusResp;
    }

    public void setConteudoResp(byte[] conteudoResp) {
        this.conteudoResp = conteudoResp;
    }

    public void setNotFound404() {
        String conteudo = "";
        conteudo = "<!DOCTYPE HTML>\n"
                + "<html>\n"
                + "   <head>\n"
                + "      <title>404 Not Found</title>\n"
                + "   </head>\n"
                + "   <body>\n"
                + "      <h1>Not Found</h1>\n"
                + "      <p>The requested URL was not found on this server.</p>\n"
                + "      <hr>\n"
                + "      <address>StoneCat/1.0 (PandiNolli) Server at Udesc Ceavi Port 80</address>\n"
                + "   </body>"
                + "</html>";
        this.conteudoResp = conteudo.getBytes();
        this.mimeTypeArq = "text/html";
        this.codStatusResp = "404 Not Found";
        this.setCabecalhoPadrao();
    }

    public void setNotImplemented() {
        String conteudo = "";
        conteudo = "<!DOCTYPE HTML>\n"
                + "<html>\n"
                + "   <head>\n"
                + "      <title>501 Not Implemented</title>\n"
                + "   </head>\n"
                + "   <body>\n"
                + "      <h1>Not Implemented</h1>\n"
                + "      <p>The requested type was not Not implemented on this server.</p>\n"
                + "      <hr>\n"
                + "      <address>StoneCat/1.0 (PandiNolli) Server at Udesc Ceavi Port 80</address>\n"
                + "   </body>"
                + "</html>";
        this.conteudoResp = conteudo.getBytes();
        this.mimeTypeArq = "text/html";
        this.codStatusResp = "501 Not Implemented";
        this.setCabecalhoPadrao();
    }

    public void setForbidden403() {
        String conteudo = "";
        conteudo = "<!DOCTYPE HTML>\n"
                + "<html>\n"
                + "   <head>\n"
                + "      <title>403 Forbidden</title>\n"
                + "   </head>\n"
                + "   <body>\n"
                + "      <h1>Forbiden</h1>\n"
                + "      <p>403forbidden403</p>\n"
                + "      <hr>\n"
                + "      <address>StoneCat/1.0 (PandiNolli) Server at Udesc Ceavi Port 80</address>\n"
                + "   </body>"
                + "</html>";
        this.conteudoResp = conteudo.getBytes();
        this.mimeTypeArq = "text/html";
        this.codStatusResp = "403 Forbidden";
        this.setCabecalhoPadrao();
    }

    public void setImTeapot() {
        String conteudo = "";
        conteudo = "<!DOCTYPE HTML>\n"
                + "<html>\n"
                + "   <head>\n"
                + "      <title>418 I'm a teapot</title>\n"
                + "   </head>\n"
                + "   <body>\n"
                + "      <h1>418 I'm a teapot</h1>\n"
                + "      <p> o servidor se recusa a preparar cafe por ser um bule de cha.</p>\n"
                + "      <hr>\n"
                + "      <address>StoneCat/1.0 (PandiNolli) Server at Udesc Ceavi Port 80</address>\n"
                + "   </body>"
                + "</html>";
        this.conteudoResp = conteudo.getBytes();
        this.mimeTypeArq = "text/html";
        this.codStatusResp = "418 I'm a teapot";
        this.setCabecalhoPadrao();

    }

    public void setServiceUnavaliable() {
        String conteudo = "";
        conteudo = "<!DOCTYPE HTML>\n"
                + "<html>\n"
                + "   <head>\n"
                + "      <title>503 Service Unavailable</title>\n"
                + "   </head>\n"
                + "   <body>\n"
                + "      <h1>503 Service Unavailable</h1>\n"
                + "      <p>Service Unavailable 503 Service Unavailable</p>\n"
                + "      <hr>\n"
                + "      <address>StoneCat/1.0 (PandiNolli) Server at Udesc Ceavi Port 80</address>\n"
                + "   </body>"
                + "</html>";
        this.conteudoResp = conteudo.getBytes();
        this.mimeTypeArq = "text/html";
        this.codStatusResp = "503 Service Unavailable";
        this.setCabecalhoPadrao();
    }

    public void setBadRequest400() {
        String conteudo = "";
        conteudo = "<!DOCTYPE HTML>\n"
                + "<html>\n"
                + "   <head>\n"
                + "      <title>400 Bad Request</title>\n"
                + "   </head>\n"
                + "   <body>\n"
                + "      <h1>400 Bad Request</h1>\n"
                + "      <p>400 Bad Request</p>\n"
                + "      <hr>\n"
                + "      <address>StoneCat/1.0 (PandiNolli) Server at Udesc Ceavi Port 80</address>\n"
                + "   </body>"
                + "</html>";
        this.conteudoResp = conteudo.getBytes();
        this.mimeTypeArq = "text/html";
        this.codStatusResp = "400 Bad Request";
        this.setCabecalhoPadrao();
    }
    
    public void setStatusSucessPost() {
        String conteudo = "";
        conteudo = "<!DOCTYPE HTML>\n"
                + "<html>\n"
                + "   <head>\n"
                + "      <title>Sucess</title>\n"
                + "   </head>\n"
                + "   <body>\n"
                + "      <h1>Sucess</h1>\n"
                + "      <p>Dados inseridos com sucesso</p>\n"
                + "      <hr>\n"
                + "      <address>StoneCat/1.0 (PandiNolli) Server at Udesc Ceavi Port 80</address>\n"
                + "   </body>"
                + "</html>";
        this.conteudoResp = conteudo.getBytes();
        this.mimeTypeArq = "text/html";
        this.codStatusResp = "200 ok";
        this.setCabecalhoPadrao();
    }

}
