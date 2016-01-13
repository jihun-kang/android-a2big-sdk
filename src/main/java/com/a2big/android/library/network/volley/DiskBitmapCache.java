package com.a2big.android.library.network.volley;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.ImageLoader;

import java.io.File;
import java.nio.ByteBuffer;

public class DiskBitmapCache extends DiskBasedCache implements ImageLoader.ImageCache {

    public DiskBitmapCache(File rootDirectory, int maxCacheSizeInBytes) {
        super(rootDirectory, maxCacheSizeInBytes);
    }

    public DiskBitmapCache(File cacheDir) {
        super(cacheDir);
    }

    public Bitmap getBitmap(String url) {
        final Entry requestedItem = get(url);
        if (requestedItem == null)
            return null;

        return BitmapFactory.decodeByteArray(requestedItem.data, 0, requestedItem.data.length);
    }

    public void putBitmap(String url, Bitmap bitmap) {
        final Entry entry = new Entry();

        ByteBuffer buffer = ByteBuffer.allocate(BitmapHelper.getSizeInBytes(bitmap));
        bitmap.copyPixelsToBuffer(buffer);
        entry.data = buffer.array();

        put(url, entry);
    }
}
