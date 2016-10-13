package com.java4game.cuadro.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

public class Atalas {

    /**
     * Вспомогательный класс, хранящий в себе все регионы текстур
     * */

	Texture mainTexture;

    ArrayList<Integer> reg1Coords = new ArrayList<Integer>(120);
    ArrayList<Integer> reg2Coords = new ArrayList<Integer>(120);
    ArrayList<Integer> reg3Coords = new ArrayList<Integer>(120);
    ArrayList<Integer> reg4Coords = new ArrayList<Integer>(120);


    //инструкция по добавлению новой текстуры


    /*
    *  после того, как мы добавили новый атлас текстур с координатами и размерами каждого региона текстур
    *  мы должны создать поле string с названием текстуры и добавить его в массив **NAMES**
    *
    * */


    public static final String sq00 = "0";
    public static final String sq01 = "1";
    public static final String sq02 = "2";
    public static final String sq03 = "3";
    public static final String sq04 = "4";
    public static final String sq05 = "5";
    public static final String sq06 = "6";
    public static final String sq07 = "7";
    public static final String sq08 = "8";
    public static final String sq09 = "9";
    public static final String sq10 = "10";
    public static final String sq11 = "11";
    public static final String squareT1 = "squareT1";
    public static final String squareT2 = "squareT2";
    public static final String squareT3 = "squareT3";
    public static final String num1 = "num1";
    public static final String num2 = "num2";
    public static final String num3 = "num3";
    public static final String num4 = "num4";
    public static final String num5 = "num5";
    public static final String num6 = "num6";
    public static final String num7 = "num7";
    public static final String num8 = "num8";
    public static final String num9 = "num9";
    public static final String col1 = "col1";
    public static final String col2 = "col2";
    public static final String col3 = "col3";
    public static final String col4 = "col4";
    public static final String starSq = "starSq";
    public static final String gemSq = "gemSq";
    public static final String comboText = "comboText";
    public static final String combNum0 = "comboText0";
    public static final String combNum1 = "comboText1";
    public static final String combNum2 = "comboText2";
    public static final String combNum3 = "comboText3";
    public static final String combNum4 = "comboText4";
    public static final String combNum5 = "comboText5";
    public static final String combNum6 = "comboText6";
    public static final String combNum7 = "comboText7";
    public static final String combNum8 = "comboText8";
    public static final String combNum9 = "comboText9";
    public static final String startB = "startB";
    public static final String startBAct = "startBAct";
    public static final String upBarMenu = "upBarMenu";
    public static final String gameNameT = "gameNameT";



    public static final String[] NAMES = new String[] {sq00, sq01, sq02, sq03, sq04, sq05, sq06, sq07, sq08, sq09, sq10, sq11, squareT1, squareT2, squareT3,
    num1, num2, num3, num4, num5, num6, num7, num8, num9, col1, col2, col3, col4, starSq, gemSq, comboText, combNum0, combNum1,
            combNum2, combNum3, combNum4, combNum5, combNum6, combNum7, combNum8, combNum9, startB, startBAct, upBarMenu, gameNameT};

    public static int textsCount;

    private final static int X = 0, Y = 1, W = 2, H = 3, NEXTELEMENT = 4;

	public Atalas() {
        String fileName = "atls1.png";
        float cff;
        cff = 1f;

        mainTexture = new Texture(fileName);




		mainTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		ArrayList<Integer> allValues = new ArrayList<Integer>();
		String allElementsString, currentElementString = "";

		Integer elementIteration[], currentElement = 0;
		elementIteration = new Integer[6];

        allElementsString = Gdx.files.internal("atls1.txt").readString();


		for (char currentElementChar : allElementsString.toCharArray()) {
			if (currentElementChar != ' ') {
				currentElementString = currentElementString + String.valueOf(currentElementChar);
			} else {
				allValues.add(Integer.parseInt(currentElementString));
				currentElementString = "";
			}
		}

		for (int i = 0; i < allValues.size() - 1; i++) {
			elementIteration[currentElement] = allValues.get(i);
			currentElement++;
			if (currentElement == NEXTELEMENT) {
				reg1Coords.add((int) (elementIteration[X] * cff));
				reg2Coords.add((int) (elementIteration[Y] * cff));
				reg3Coords.add((int) (elementIteration[W] * cff));
				reg4Coords.add((int) (elementIteration[H] * cff));

                currentElement = X;
			}

		}
		elementIteration[3] = allValues.get(allValues.size() - 1);

        reg1Coords.add((int) (elementIteration[0] * cff));
        reg2Coords.add((int) (elementIteration[1] * cff));
        reg3Coords.add((int) (elementIteration[2] * cff));
        reg4Coords.add((int) (elementIteration[3] * cff));
        textsCount = reg1Coords.size();
	}
	
	public void dispose(){
		mainTexture.dispose();
	}

	public TextureRegion getTG(int numTexture) {
		return new TextureRegion(mainTexture, reg1Coords.get(numTexture), reg2Coords.get(numTexture), reg3Coords.get(numTexture),
				reg4Coords.get(numTexture));
	}
}
