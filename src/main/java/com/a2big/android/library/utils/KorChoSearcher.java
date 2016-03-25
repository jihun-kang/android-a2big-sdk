package com.a2big.android.library.utils;

/**
 * Created by a2big on 16. 3. 25..
 */
public class KorChoSearcher {

    //                                              ㄱ      ㄲ      ㄴ      ㄷ      ㄸ      ㄹ      ㅁ      ㅂ       ㅃ       ㅅ      ㅆ      ㅇ      ㅈ       ㅉ      ㅊ      ㅋ       ㅌ      ㅍ      ㅎ
    private final static char[] ChoSung = { 0x3131, 0x3132, 0x3134, 0x3137, 0x3138, 0x3139, 0x3141, 0x3142, 0x3143, 0x3145, 0x3146, 0x3147, 0x3148, 0x3149, 0x314a, 0x314b, 0x314c, 0x314d, 0x314e };
    //                                              ㅏ      ㅐ      ㅑ      ㅒ      ㅓ      ㅔ      ㅕ      ㅖ       ㅗ       ㅘ      ㅙ      ㅚ      ㅛ       ㅜ      ㅝ      ㅞ       ㅟ      ㅠ      ㅡ      ㅢ      ㅣ
    private final static char[] JungSung = { 0x314f, 0x3150, 0x3151, 0x3152, 0x3153, 0x3154, 0x3155, 0x3156, 0x3157, 0x3158, 0x3159, 0x315a, 0x315b, 0x315c, 0x315d, 0x315e, 0x315f, 0x3160, 0x3161, 0x3162, 0x3163 };
    //                                                ㄱ      ㄲ      ㄳ      ㄴ      ㄵ      ㄶ      ㄷ       ㄹ       ㄺ      ㄻ      ㄼ      ㄽ       ㄾ      ㄿ      ㅀ       ㅁ       ㅂ      ㅄ      ㅅ      ㅆ      ㅇ      ㅈ       ㅊ      ㅋ      ㅌ       ㅍ      ㅎ
    private final static char[] JongSung = { 0, 0x3131, 0x3132, 0x3133, 0x3134, 0x3135, 0x3136, 0x3137, 0x3139, 0x313a, 0x313b, 0x313c, 0x313d, 0x313e, 0x313f, 0x3140, 0x3141, 0x3142, 0x3144, 0x3145, 0x3146, 0x3147, 0x3148, 0x314a, 0x314b, 0x314c, 0x314d, 0x314e };

    public KorChoSearcher() {
    }

    /**
     * 한글의 제일 앞 자음을 가져온다
     */
    public static String getJaso(String $str)
    {
        int a; // 자소
        if( $str == null) return null;
        String result = $str;
        try{
            char ch = $str.charAt(0);
            // "AC00:가" ~ "D7A3:힣" 에 속한 글자면 분해
            if (ch >= 0xAC00 && ch <= 0xD7A3)
            {
                a = (ch - 0xAC00) / (21 * 28);
                result = ChoSung[a] + "";
            }

        }catch(StringIndexOutOfBoundsException siobe){
            System.out.println("invalid input");
            result = null;
        }

        return result;
    }

    /**
     * 한글 자소 분리
     * @param s
     * @return 김태희 -> ㄱㅣㅁㅌㅐㅎㅢ
     */
    public static String hangulToJaso(String $str)
    {
        // 유니코드 한글 문자열을 입력 받음
        int a, b, c; // 자소 버퍼: 초성/중성/종성 순
        String result = "";

        for (int i = 0; i < $str.length(); i++)
        {
            char ch = $str.charAt(0);

            if (ch >= 0xAC00 && ch <= 0xD7A3)
            { // "AC00:가" ~ "D7A3:힣" 에 속한 글자면 분해
                c = ch - 0xAC00;
                a = c / (21 * 28);
                c = c % (21 * 28);
                b = c / 28;
                c = c % 28;
                result = result + ChoSung[a] + JungSung[b];
                if (c != 0)
                    result = result + JongSung[c]; // c가 0이 아니면, 즉 받침이 있으면
            }
            else
            {
                result = result + ch;
            }
        }
        return result;
    }
}
