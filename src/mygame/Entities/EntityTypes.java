package mygame.Entities;

public enum EntityTypes {
    Enemy1(new String[] {"Enemy0-0", "Enemy0-1", "Enemy0-2", "Enemy0-3", "Enemy0-4", "Enemy0-6", "Enemy0-7", "Enemy0-8", "Enemy0-9"}, 1f, 2f, 1f),
    Enemy2(new String[] {"Enemy1-0", "Enemy1-1", "Enemy1-2", "Enemy1-3", "Enemy1-4", "Enemy1-5", "Enemy1-6", "Enemy1-7", "Enemy1-8"}, 1f, 2f, 1f),
    Bullet(new String[] {"bullet", "bullet", "bullet"}, 0.1f, 0.1f, 2f);
    
    private String[] textureNames;
    private float width;
    private float height;
    private float weight;
    private EntityTypes(String[] textureNames, float width, float height, float weight) {
        this.textureNames = textureNames;
        this.width = width * 2;
        this.height = height * 2;
        this.weight = weight;
    }

    public String[] getTextureNames() {
        return textureNames;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getWeight() {
        return weight;
    }
}
