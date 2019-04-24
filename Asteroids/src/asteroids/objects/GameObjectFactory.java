/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.objects;
import asteroids.core.GameObject;
import asteroids.core.HUD;

/**
 *
 * @author 5586658
 */
public class GameObjectFactory {
    
    public enum TipoGameObject {
        INIMIGO,
        TIRO,
        NAVE,
        EXPLOSAO,
        UFO,
        HUD
    }
    
    public static GameObject createGameObject(TipoGameObject tipo){
        switch(tipo){
            case INIMIGO: return createEnemy();
            case TIRO: return new TiroDaNave();
            case NAVE: return new Nave();
            case EXPLOSAO: return Explosao.criaExplosao();
            case HUD: return new HUD();
        }
        throw new IllegalArgumentException("Valor de enumeração não existe!");
    }
    
    private static GameObject createEnemy(){
        double sorteio = Math.random();
        return (sorteio <= 0.5) ? new Asteroid() : new UFO();
    }
}
