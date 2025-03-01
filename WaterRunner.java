import java.util.Map;
import org.rsbot.script.Script;
import org.rsbot.script.ScriptManifest;
import org.rsbot.event.listeners.ServerMessageListener;
import org.rsbot.event.events.ServerMessageEvent;
import org.rsbot.script.wrappers.RSObject;
import org.rsbot.script.wrappers.RSTile;
import org.rsbot.script.wrappers.RSArea;
import org.rsbot.script.wrappers.RSNPC;
import org.rsbot.script.wrappers.RSTilePath;
import java.awt.*;
import org.rsbot.event.events.MessageEvent;
import org.rsbot.event.listeners.MessageListener;
import org.rsbot.event.listeners.PaintListener;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import org.rsbot.script.wrappers.RSInterface;
import org.rsbot.script.methods.*;

@ScriptManifest(authors = "sniperkid1", name = "WaterRunner", version = 1.0, description = "Mines rune essence at the wizard's tower and crafts into water runes.")
    public class WaterRunner extends Script {

    public boolean onStart() {
        log("Welcome, please start at the wizard's tower");
        return true;
    }
    int bigRock = 2491;
    int[] pickID = {1265, 1267, 1269, 1271, 1273, 1275};
    //int waterTal = ;
    int altar = 2480;
    int ruins = 2454;
    int[] ePortal = {2492, 7389};
    int[] wPortal = {2467, 7389};
    int largeDoorC = 33060;
    int largeDoorO = 31828;
    int uLadder = 32015;
    int dLadder = 2147;
    int doorID = 11993;
    int wizard = 300;
    int rEss = 1436;
    int doorOID = 11994;
    RSTile[] towerToAltar = { new RSTile(3113, 3171), new RSTile(3113,3183),
            new RSTile(3113,3190), new RSTile(3113,3197), new RSTile(3115,3206), new RSTile(3120,3208), new RSTile(3130,3206), new RSTile(3137,3202),
            new RSTile(3141,3196), new RSTile(3146,3188), new RSTile(3153,3179), new RSTile(3162,3170), new RSTile(3168,3166),
            new RSTile(3174,3165), new RSTile(3182,3165)};
    RSTile[] midToEss = { new RSTile(2917, 4838), new RSTile(2921,4843), new RSTile(2923,4848)};
    RSTile[] toDoor = {new RSTile(3108,9572)};
    int loops = 0;
                            private void talkWiz() {
                                RSNPC wiz = npcs.getNearest(wizard);
                                RSTile atdoor = new RSTile(3109,9572);
                                RSObject doorC = objects.getNearest(largeDoorC);
                                RSObject doorO = objects.getNearest(largeDoorO);
                                if(atlad1()){
                                        walking.walkTileMM(atdoor);
                                            sleep(random(1200,1400));
                                }
                                if(doorC !=null){
                                    doorC.doAction("Open");
                                sleep(random(1000,1400));
                                }
                                if(!wiz.isOnScreen())
                                   camera.turnToCharacter(wiz);
                                    sleep(1500,2000);
                                    
                                if(wiz.isOnScreen() && atWiz1()){
                                    wiz.doAction("Teleport");
                                    sleep(1500,2000);
                                }
                                    }
                            private boolean walkDoor(){
                                RSTile[] randomizedPath = walking.randomizePath(toDoor, 2, 2);
                                return walking.walkPathMM(randomizedPath, 15);
                            }
                                    private boolean atMine() {
                                        RSArea area = new RSArea(new RSTile(2886, 4807), new RSTile(2933, 4858));
                                        return area.contains(getMyPlayer().getLocation());
                                    }
                                                private void mineEss() {
                                                    RSObject ess = objects.getNearest(bigRock);
                                                    camera.turnToObject(ess);
                                                    if (players.getMyPlayer().getAnimation() == -1){
                                                    ess.doAction("Mine");
                                                    sleep(random(700,1000));
                                                    }
                                                }
                                                private boolean atWiz1(){
                                                    RSArea area = new RSArea(new RSTile(3096, 9567), new RSTile(3107, 9574));
                                                        return area.contains(getMyPlayer().getLocation());
                                                }
                                                private boolean atlad1(){
                                                    RSArea area = new RSArea(new RSTile(3103, 9575), new RSTile(3110, 9577));
                                                        return area.contains(getMyPlayer().getLocation());
                                                        }
                                                        private void goOutMine() {
                                                            RSObject port = objects.getNearest(ePortal);
                                                            if (calc.tileOnScreen(port.getLocation())
                                                                          && getMyPlayer().getAnimation() == -1) {
                                                                        port.doAction("Enter");}
                                                                    if (!calc.tileOnScreen(port.getLocation())) {
                                                                    if (calc.distanceTo(port) <= 7) {
                                                                        camera.turnToObject(port);
                                                                        } else {
                                                                         if (!getMyPlayer().isMoving()) {
                                                                            walking.walkTileMM(port.getLocation());
                                                                            sleep(random(2500,2900));
                                                                        }
                                                                    }
                                                                     }
                                                            port.doAction("Enter");
                                                            sleep(random(600, 800));
                                                        }
                                                                    private void upLadder() {
                                                                        RSObject lDoor = objects.getNearest(largeDoorC);
                                                                        RSObject ladder = objects.getNearest(uLadder);
                                                                        RSObject door1 = objects.getTopAt(new RSTile(3108,9570));
                                                                        if (door1==null && (players.getMyPlayer().getAnimation() == -1)){
                                                                            camera.setAngle(random(300,340));
                                                                            ladder.doAction("Climb-up");
                                                                            sleep(random(600,800));
                                                                        }
                                                                        if (door1 !=null){
                                                                            door1.doAction("Open");
                                                                            sleep(1500, 2000);
                                                                            //setCompass(W);
                                                                            
                                                                            ladder.doAction("Climb-up");
                                                                            sleep(random(600,800));
                                                                        }

                                                                    }
                                                                                private boolean walkToAltar() {
                                                                                    RSTile[] randomizedPath = walking.randomizePath(towerToAltar, 3, 3);
                                                                                    return walking.walkPathMM(randomizedPath, 15);
                                                                                }
                                                                                        private boolean atAltar() {
                                                                                            RSArea area = new RSArea(new RSTile(3478, 4825), new RSTile(3497, 4837));
                                                                                            return area.contains(getMyPlayer().getLocation());
                                                                                        }
                                                                            private void enterAltar() {
                                                                                RSObject ruin = objects.getNearest(ruins);
                                                                                ruin.doAction("Enter");
                                                                                sleep(random(1000,1500));
                                                                            }
                                                                 private void craft() {
                                                                     RSObject wAltar = objects.getNearest(altar);
                                                                     RSTile tile = new RSTile(3489,4833);
                                                                     //RSTile alt = walking.getNearestTileOnMap(new RSTile(3486, 4835));
                                                                     if (calc.tileOnScreen(wAltar.getLocation())
                                                                          && getMyPlayer().getAnimation() == -1) {
                                                                        wAltar.doAction("Craft-rune");
                                                                     sleep(random(1500,2000));
                                                                     }
                                                                    if (!calc.tileOnScreen(wAltar.getLocation())) {
                                                                    if (calc.distanceTo(wAltar) <= 7) {
                                                                        camera.turnToTile(wAltar.getLocation());
                                                                        } else {
                                                                         if (!getMyPlayer().isMoving() && !wAltar.isOnScreen()) {
                                                                            walking.walkTileMM(tile);
                                                                            sleep(random(2300,2900));
                                                                        }
                                                                    }
                                                                     }
                                                                     if (!wAltar.isOnScreen()){
                                                                         walking.walkTileMM(tile);
                                                                         sleep(random(600,800));
                                                                     }

                                                                     //if (!wAltar.isOnScreen()){
                                                                     //walking.walkTileMM(alt);
                                                                     //sleep(random(1000,1200));
                                                                     //}
                                                                     //if(wAltar.isOnScreen()){
                                                                     //wAltar.doAction("Craft-rune");
                                                                     //sleep(random(1800, 2600));
                                                                 //}
                                                                    }
                                                                        private void leaveAltar() {
                                                                            RSObject port = objects.getNearest(wPortal);
                                                                            //RSTile tPort = walking.getNearestTileOnMap(new RSTile(3494, 4833));
                                                                            if (calc.tileOnScreen(port.getLocation())
                                                                          && getMyPlayer().getAnimation() == -1) {
                                                                        port.doAction("Enter");}
                                                                    if (!calc.tileOnScreen(port.getLocation())) {
                                                                    if (calc.distanceTo(port) <= 7) {
                                                                        camera.turnToTile(port.getLocation());
                                                                        } else {
                                                                         if (!getMyPlayer().isMoving()) {
                                                                            walking.walkTileMM(port.getLocation());
                                                                            sleep(random(2000,2600));
                                                                        }
                                                                    }
                                                                     }
                                                                            //if(!port.isOnScreen()){
                                                                            //    walking.walkTileMM(port.getLocation());
                                                                             //   sleep(random(1500,1900));
                                                                            
                                                                            if(port.isOnScreen()){
                                                                            port.doAction("Enter");
                                                                            sleep(random(1000,1300));
                                                                                }
                                                                                }
                                                                private void walkToTower() {
                                                                    RSTilePath newPath = walking.newTilePath(towerToAltar);
                                                                    newPath.reverse();
                                                                        newPath.randomize(2, 2);
                                                                        newPath.traverse();
                                                                }
                                        private boolean atWiz() {
                                        RSArea area = new RSArea(new RSTile(3096, 9566), new RSTile(3110,9577));
                                        return area.contains(getMyPlayer().getLocation());
                                    }




                                        private void downLadder() {
                                            RSObject doorClosed = objects.getNearest(doorID);
                                            RSObject doorOpened = objects.getNearest(doorOID);
                                            RSObject door1 = objects.getTopAt(new RSTile(3109,3167));
                                            RSObject dLadd = objects.getNearest(dLadder);
                                            RSObject door2 = objects.getTopAt(new RSTile(3107,3162));
                                            RSObject door1o = objects.getTopAt(new RSTile(3109, 3166));
                                            RSObject door2o = objects.getTopAt(new RSTile(3108,3162));
                                            if(door1 !=null){
                                            door1.doAction("Open");
                                            sleep(random(1300,1800));
                                            }
                                            if(door2 != null){
                                                door2.doAction("Open");
                                                sleep(random(1000,1500));
                                            }
                                            if (door1 ==null && door2 ==null){
                                                //walking.walkTileMM(dLadd.getLocation());
                                            dLadd.doAction("Climb-down");
                                            sleep(random(1500, 2000));
                                        }
    }
                                                           private boolean atRuins() {
                                                                   RSArea area = new RSArea(new RSTile(3177, 3156), new RSTile(3189, 3168));
                                                                   return area.contains(getMyPlayer().getLocation());
                                                                   }
                                                                        private boolean atTower() {
                                                                             RSArea area = new RSArea(new RSTile(3099, 3156), new RSTile(3119, 3173));
                                                                             return area.contains(getMyPlayer().getLocation());
                                                                            }
                                                          private boolean atMiddle() {
                                                                   RSArea area = new RSArea(new RSTile(2909,4829), new RSTile(2917,4838));
                                                                   return area.contains(getMyPlayer().getLocation());
                                                                   }
                                                    private boolean walkToEss() {
                                                        RSTile[] randomizedPath = walking.randomizePath(midToEss, 3, 3);
                                                                 return walking.walkPathMM(randomizedPath, 15);
                                                    }
                                                    private boolean atLadd(){
                                                        RSArea area = new RSArea(new RSTile(3103, 3160), new RSTile(3111, 3166));
                                                                   return area.contains(getMyPlayer().getLocation());
                                                    }
                                             private void goOutside(){
                                                 RSObject doorClosed = objects.getNearest(doorID);
                                            RSObject doorOpened = objects.getNearest(doorOID);
                                            RSObject door1 = objects.getTopAt(new RSTile(3109,3167));
                                            RSObject dLadd = objects.getNearest(dLadder);
                                            RSObject door2 = objects.getTopAt(new RSTile(3107,3162));
                                            RSObject door1o = objects.getTopAt(new RSTile(3109, 3166));
                                            RSObject door2o = objects.getTopAt(new RSTile(3108,3162));
                                            RSObject table = objects.getNearest(12543);
                                            RSTile out = new RSTile(3111,3169);
                                            if(door2 != null){
                                                door2.doAction("Open");
                                                
                                                sleep(random(1000,1500));
                                            }
                                            camera.turnToObject(table);
                                            if(door1 !=null){
                                            door1.doAction("Open");
                                            sleep(random(1300,1800));
                                            }
                                            if (door1 ==null && door2 ==null){
                                                walking.walkTileMM(out);
                                            }
                                             }
    public void onFinish() {
        log("Thanks for using my script!");
    }

        @Override
        public int loop() {
        	loops++;
            if(atAltar() && inventory.contains(rEss)){
                craft();
            }
            if(atAltar() && !inventory.contains(rEss)) {
                leaveAltar();
            }
            if(atMine() && inventory.isFull()){
                goOutMine();
            }
            if(atMine() && !inventory.isFull()){
                mineEss();
            }
            if(atWiz() && inventory.isFull()){
                upLadder();
            }
            if(atWiz() && !inventory.isFull()){
                talkWiz();
            }
            if(atTower() && !inventory.isFull()){
                downLadder();
            }
            if(atTower() && atLadd() && inventory.isFull()){
                goOutside();
            }
            if(atTower() && !atLadd() && inventory.isFull()){
                walkToAltar();
            }
            if(!atTower() && !atAltar() && !atMine() && !atRuins() && inventory.isFull()){
                walkToAltar();
            }
            if(!atTower() && !atAltar() && !atMine() && !atRuins() && !inventory.isFull()){
                walkToTower();
            }
            if(atRuins() && inventory.isFull()){
                enterAltar();
            }
            if(atRuins() && !inventory.isFull()){
                walkToTower();
            }
            if(atMiddle()){
                walkToEss();
            }
            if (!walking.isRunEnabled() && walking.getEnergy() > 60) {
			walking.setRun(true);
            }
            if (loops % 5 == 0) { 
                camera.setAngle(random(50,20),random(50,20)); 
            }
            
            return 800;
        }
}
