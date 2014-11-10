package mygame.Entities;

import com.jme3.audio.AudioNode;
import com.jme3.math.Vector3f;
import mygame.Config.AudioMap;
import mygame.Main;
import mygame.Config.Options;

public class Enemy extends Entity {
    private Main game;
    private Player player;
    private AudioNode attack;

    public Enemy(Main game, float x, float y) {
        super(game, EntityTypes.Enemy2, x, y);
        this.game = game;
        this.player = game.getPlayer();
        this.attack = audioMap.getAudio(AudioMap.Types.AXE);
                
        this.physics.setFriction(0f);
    }

    @Override
    public void updateEntity(float tpf) {
        if (player.getLocation().subtract(this.physics.getPhysicsLocation()).length() < Options.ENEMY_SPOT_RADIUS) {
            Vector3f vel = new Vector3f(player.getLocation().subtract(this.physics.getPhysicsLocation()).normalize().x * 5, 0, 0);
            this.physics.setLinearVelocity(this.physics.getLinearVelocity().setX(vel.x));
        }
    }

    @Override
    public void actOnCollision(Entity e) {
        if (e.getClass() == Player.class) {
            Vector3f impulse = this.physics.getLinearVelocity().clone();
            e.physics.applyImpulse(impulse.setY(0).normalize().mult(3), Vector3f.ZERO);
            e.setDamage(1);
            attack.play();
        }
    }

    @Override
    public void death() {
        player.increaseScore(100);
        this.physics.setEnabled(false);
        removeFromParent();
        game.spawnEnemy();
        game.spawnEnemy();
    }
    
    @Override
    public Enemy clone() {
        return new Enemy(game, getLocation().x, getLocation().y);
    }
}
