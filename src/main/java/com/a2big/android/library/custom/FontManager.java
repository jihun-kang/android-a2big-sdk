package com.a2big.android.library.custom;

import android.content.res.AssetManager;
import android.graphics.Typeface;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by a2big on 16. 7. 14..
 */
public class FontManager {
    private static FontManager instance;

    private AssetManager mgr;

    private Map<String, Typeface> fonts;

    private FontManager(AssetManager _mgr) {
        mgr = _mgr;
        fonts = new HashMap<String, Typeface>();
    }

    public static void init(AssetManager mgr) {
        instance = new FontManager(mgr);
    }

    public static FontManager getInstance() {
        return instance;
    }

    public Typeface getFont(String asset) {
        if (fonts.containsKey(asset))
            return fonts.get(asset);

        Typeface font = null;

        try {
            font = Typeface.createFromAsset(mgr, asset);
            fonts.put(asset, font);
        } catch (Exception e) {

        }

        if (font == null) {
            try {
                String fixedAsset = fixAssetFilename(asset);
                font = Typeface.createFromAsset(mgr, fixedAsset);
                fonts.put(asset, font);
                fonts.put(fixedAsset, font);
            } catch (Exception e) {

            }
        }

        return font;
    }

    private String fixAssetFilename(String asset) {
        // Empty font filename?
        // Just return it. We can't help.
        if (StringUtils.isEmpty(asset))
            return asset;

        // Make sure that the font ends in '.ttf' or '.ttc'
        if ((!asset.endsWith(".ttf")) && (!asset.endsWith(".ttc")))
            asset = String.format("%s.ttf", asset);

        return asset;
    }
}