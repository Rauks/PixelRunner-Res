/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlleveltool;

/**
 *
 * @author Karl
 */
public class LevelElement {
    public static final int MAX_LAYER = 10;
    public static final int MIN_HEIGHT_PLATEFORM = 3;
    
    public enum LevelTypes{
        JUMP(1, "BonusJump"),
        LIFE(2, "BonusLife"),
        SLOW(3, "BonusSlow"),
        SPEED(4, "BonusSpeed"),
        SWAP(9, "BonusSwap"),
        PLATFORM(5, "Platform"),
        ROCKET(6, "Rocket"),
        TRAP(7, "Trap"),
        WALL(8, "Wall");
        
        
        private String name;
        private int id;
        private LevelTypes(int id, String name){
            this.id = id;
            this.name = name;
        }
        public int getId(){
            return this.id;
        }

        @Override
        public String toString() {
            return name;
        }
        
    }
    
    private LevelTypes type;
    private int layer;

    public LevelElement(LevelTypes type, int layer) throws Exception {
        if(layer < MAX_LAYER){
            if(type == LevelTypes.PLATFORM){
                if(layer >= MIN_HEIGHT_PLATEFORM && layer < MAX_LAYER) {
                    this.type = type;
                    this.layer = layer;
                }
                else{
                    throw new Exception("ELEMENT ERROR");
                }
            }
            else{
                this.type = type;
                this.layer = layer;
            }
        }
        else{
            throw new Exception("ELEMENT ERROR");
        }
    }

    public LevelTypes getType() {
        return type;
    }

    public int getLayer() {
        return layer;
    }

    @Override
    public String toString() {
        return this.type.toString() + "(" + this.layer + ")";
    }
}
