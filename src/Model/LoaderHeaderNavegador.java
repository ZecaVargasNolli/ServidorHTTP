package Model;

/**
 *
 * @author Giancarlo
 */
public class LoaderHeaderNavegador {

    private static HeaderNavegador header; /* header que sempre sera utilzado como retorno do metodo 'loadHeaderNavegador' */
    
    public static HeaderNavegador load(String requisicao) {
        LoaderHeaderNavegador.header = new HeaderNavegador();
        /* pegando todas as linhas da requisicao */
        String[] linhasReq = requisicao.split("\r\n");
        LoaderHeaderNavegador.setMetodo(linhasReq[0]);
        LoaderHeaderNavegador.setCaminho(linhasReq[0]);
        LoaderHeaderNavegador.setVersaoHttp(linhasReq[0]);
        LoaderHeaderNavegador.setConteudo(linhasReq[linhasReq.length-1]);
        
        return LoaderHeaderNavegador.header;
    }

    private static void setMetodo(String linhaHeader) {
        LoaderHeaderNavegador.header.setMetodo(linhaHeader.split(" ")[0]);
    }

    private static void setCaminho(String linhaHeader) {
        LoaderHeaderNavegador.header.setCaminho(linhaHeader.split(" ")[1].replaceAll("%20", " "));
    }
    
    private static void setVersaoHttp(String linhaHeader) {
        LoaderHeaderNavegador.header.setVersaoHttp(linhaHeader.split(" ")[2]);
    }
    
    private static void setConteudo(String linhaHeader) {
        if(LoaderHeaderNavegador.header.getMetodo().equals("POST")){
            LoaderHeaderNavegador.header.setConteudo(linhaHeader);
        }
        else {
            LoaderHeaderNavegador.header.setConteudo("");
        }

    }

   
}
