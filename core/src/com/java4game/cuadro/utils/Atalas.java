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

	Texture mn;

    ArrayList<Integer> q1q = new ArrayList<Integer>(120);
    ArrayList<Integer> q2q = new ArrayList<Integer>(120);
    ArrayList<Integer> q3q = new ArrayList<Integer>(120);
    ArrayList<Integer> q4q = new ArrayList<Integer>(120);


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



    public static final String[] NAMES = new String[] {sq00, sq01, sq02, sq03, sq04, sq05, sq06, sq07, sq08, sq09, sq10, sq11, squareT1, squareT2, squareT3,
    num1, num2, num3, num4, num5, num6, num7, num8, num9, col1, col2, col3, col4};

    public static int textsCount;


	public Atalas() {
        String file;
        float cff;


        file = "atls1.png";
        cff = 1f;

        mn = new Texture(file);




		mn.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		ArrayList<Integer> numb = new ArrayList<Integer>();

		String str, str2 = "";

		Integer hq[], jd = 0;

		hq = new Integer[6];

        str = Gdx.files.internal("atls1.txt").readString();


		for (char element : str.toCharArray()) {
			if (element != ' ') {
				str2 = str2 + String.valueOf(element);
			} else {
				numb.add(Integer.parseInt(str2));
				str2 = "";
			}
		}

		for (int i = 0; i < numb.size() - 1; i++) {
			hq[jd] = numb.get(i);
			jd++;
			if (jd == 4) {
				jd = 0;



				q1q.add((int) (hq[0] * cff));
				q2q.add((int) (hq[1] * cff));
				q3q.add((int) (hq[2] * cff));
				q4q.add((int) (hq[3] * cff));

			}

		}
		hq[3] = numb.get(numb.size() - 1);

        q1q.add((int) (hq[0] * cff));
        q2q.add((int) (hq[1] * cff));
        q3q.add((int) (hq[2] * cff));
        q4q.add((int) (hq[3] * cff));
        textsCount = q1q.size();

        numb = null;
	}
	
	public void dispose(){
        q1q = null;
        q2q = null;
        q3q = null;
        q4q = null;
		mn.dispose();
	}

	public TextureRegion getTG(int wg) {
		return new TextureRegion(mn, q1q.get(wg), q2q.get(wg), q3q.get(wg),
				q4q.get(wg));
	}
}
