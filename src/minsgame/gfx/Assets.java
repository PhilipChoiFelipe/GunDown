/*
music, pics of game
300 x 300 6 x 6 
 */
package minsgame.gfx;

import java.awt.image.BufferedImage;

/**
 * 사전에 만들어 놓은 Sprite sheet 을 자연스럽게 쪼개서 깔끔하게 정리해놓은 클래스. 
 * 타일이나, 엔티티 클래스에서 이 image 파일이 필요하다면, Assets 을 불러 사용하면된다. 
 */
public class Assets {
    private static final int width = 50;
    private static final int height = 50;
    public static BufferedImage player_standing , sadPlayer, dirt, grass, stone, tree, dark, nightSky, guardRoomFloor,
            streetLamp, extinguisher, trashCan, computer, weaponCloset;
    public static BufferedImage heart;
    public static BufferedImage[] player_down, player_up, player_left, player_right;
    public static BufferedImage[] slime, slime_hurt;
    public static BufferedImage[] runner_normal, runner_angry;
    public static BufferedImage[] batting_down, batting_up, batting_left, batting_right;
    public static BufferedImage[] btn_start;
    public static void init(){
        ImageLoader imageLoader = new ImageLoader();
        //일단 spritesheet 을 imageloader 클래스로 부른뒤 Crop 으로 짜른다. 
        SpriteSheet sheet = new SpriteSheet(imageLoader.loadImage("/textures/spriteSheet.v1.4.png"));
        SpriteSheet playerSheet = new SpriteSheet(imageLoader.loadImage("/textures/playerSpriteSheet.png"));
        SpriteSheet attackSheet = new SpriteSheet(imageLoader.loadImage("/textures/cleanPlayerAttackingSheet.png"));
        SpriteSheet monsterSheet = new SpriteSheet(imageLoader.loadImage("/textures/cleanMonsterSheet.v.1.1.png"));
//        SpriteSheet mainPlayerSheet = new SpriteSheet(ImageLoader.loadImage("/textures/mainCharacter.v1.0.png"));
        //Start Button
        btn_start = new BufferedImage[2];
        btn_start[0] = sheet.crop(width * 3, 0, width * 2, height);
        btn_start[1] = sheet.crop(width * 3, height, width * 2, height);
        
        //Standing
//        player_standing = playerSheet.crop(2, 0, width -4 , height- 2);
        player_standing = playerSheet.crop(2, 0, width - 4, height - 2);
        //player Down
//        player_down = new BufferedImage [2];
//        player_down[0] = playerSheet.crop(width + 2, 0, width -4, height-2);
//        player_down[1] = playerSheet.crop(width * 2 + 2, 0, width -4, height -2);]
        player_down = new BufferedImage [2];
        player_down[0] = playerSheet.crop(width + 2, 0, width -4, height-2);
        player_down[1] = playerSheet.crop(width * 2 + 2, 0, width -4, height -2);
        //player Up
        player_up = new BufferedImage [2];
        player_up[0] = playerSheet.crop(width +2, height + 2, width -4, height - 4);
        player_up[1] = playerSheet.crop(width * 2 +2, height + 2, width -4, height - 4);
        //player Left
        player_left = new BufferedImage [2];
        player_left[0] = playerSheet.crop(width +2, height * 3 +4, width -4, height -4);
        player_left[1] = playerSheet.crop(width * 2 +2, height * 3+4, width-4, height-4);
        //player Right
        player_right = new BufferedImage [2];
        player_right[0] = playerSheet.crop(width +2, height * 2+4, width-4, height-4);
        player_right[1] = playerSheet.crop(width * 2 +2, height * 2 +4, width-4, height-4);
        
        //Player batting (Attack) 
        batting_down = new BufferedImage [3];
        batting_down[0] = attackSheet.crop(0, 0, width, height);
        batting_down[1] = attackSheet.crop(width, 0, width, height);
        batting_down[2] = attackSheet.crop(width * 2, 0, width, height);
        
        //Batting Up
        batting_up = new BufferedImage [3];
        batting_up[0] = attackSheet.crop(width * 3, 0, width, height);
        batting_up[1] = attackSheet.crop(width * 4, 0, width, height);
        batting_up[2] = attackSheet.crop(width * 5, 0, width, height);
        
        //Batting right
        batting_right = new BufferedImage [3];
        batting_right[0] = attackSheet.crop(0 , height, width, height);
        batting_right[1] = attackSheet.crop(width, height, width, height);
        batting_right[2] = attackSheet.crop(width * 2, height, width, height);
        
        //Batting left
        batting_left = new BufferedImage [3];
        batting_left[0] = attackSheet.crop(width * 3 , height, width, height);
        batting_left[1] = attackSheet.crop(width * 4, height, width, height);
        batting_left[2] = attackSheet.crop(width * 5, height, width, height);
        
        //Monster
        //Slime
        slime = new BufferedImage [1];
        slime[0] = monsterSheet.crop(0, 0, width, height);
        
        slime_hurt = new BufferedImage [2];
        slime_hurt[0] = monsterSheet.crop(width, 0, width, height);
        slime_hurt[1] = monsterSheet.crop(width, 0, width, height);
        
        //Runner
        runner_normal = new BufferedImage[2];
        runner_normal[0] = monsterSheet.crop(width, height, width, height);
        runner_normal[1] = monsterSheet.crop(width * 2, height, width, height);
        runner_angry = new BufferedImage[2];
        runner_angry[0] = monsterSheet.crop(width, height * 2, width, height);
        runner_angry[1] = monsterSheet.crop(width * 2, height * 2, width, height);
        //Item
        heart = sheet.crop(width, height * 2, width, height);
        
        //Tile
        grass = sheet.crop(0, 0, width, height);
        dirt = sheet.crop(width , 0, width, height);
        stone = sheet.crop(width * 2, 0, width, height);
        tree = sheet.crop(0, height, width, height);
        nightSky = sheet.crop(width * 4 + 2, height * 3 + 2, width - 2, height - 2);
        dark = sheet.crop(width * 5, height * 3 + 3, width, height-3);
        guardRoomFloor = sheet.crop(5, height * 3 + 2, width - 5, height - 2);
        
        //Static Entities
        streetLamp = sheet.crop(2, height + 2, width - 3, height * 2 - 3);
        extinguisher = sheet.crop(width + 2, height + 2, width - 2, height - 2);
        trashCan = sheet.crop(width * 2 + 2, height + 2, width - 3, height - 3);
        computer = sheet.crop(width, height * 3, width * 2, height * 2);
        weaponCloset = sheet.crop(width * 3, height * 3, width, height * 2);
    }
}
