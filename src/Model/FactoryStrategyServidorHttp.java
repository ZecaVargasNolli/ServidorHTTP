
package Model;

/**
 *
 * @author Giancarlo
 */
public class FactoryStrategyServidorHttp {
    
    public static StrategyServidorHttp getStrategyServidorHttp(String tipoRequisicao){
        StrategyServidorHttp retorno = null;
        switch(tipoRequisicao){
            case "GET":
                retorno = new StrategyServidorHttpGET();
                break;
            case "POST":
                retorno = new StrategyServidorHttpPOST();
                break;
            default:
                retorno = new StrategyServidorHttpNotImplemented();
                break;
        }
        
        return retorno;
    }
    
}
