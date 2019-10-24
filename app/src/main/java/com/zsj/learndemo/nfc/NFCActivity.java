package com.zsj.learndemo.nfc;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.zsj.learndemo.R;
import com.zsj.learndemo.utils.ImageUtils;

import java.io.File;
import java.net.URI;

public class NFCActivity extends AppCompatActivity {

    private Uri[] mFileUris = new Uri[10];
    private NfcAdapter mNfcAdapter;
    private FileUriCallback mFileUriCallback;
    private static final int GALLERY_REQUEST_CODE = 0;    // 相册选图标记
    private File mParentPath;
    private Intent mIntent;
    private String TAG = "zsj";
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);
        imageView = findViewById(R.id.iv_nfc);
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);


        boolean s = getPackageManager().hasSystemFeature(PackageManager.FEATURE_NFC);
        Log.d(TAG, "onCreate: "+s);
        handleViewIntent();
    }

    public void onSelectPhoto(View view) {
        String transferFile = "transferimage.jpg";
        File extDir = getExternalFilesDir(null);
        File requestFile = new File(extDir, transferFile);
        requestFile.setReadable(true, false);
        // Get a URI for the File and add it to the list of URIs
        Uri fileUri = Uri.fromFile(requestFile);
        if (fileUri != null) {
            mFileUris[0] = fileUri;
        } else {
            Log.e("My Activity", "No File URI available for file.");
        }
    }

    public void onSendFile(View view) {
        if(mNfcAdapter==null){
            return;
        }
        mFileUriCallback = new FileUriCallback();
        mNfcAdapter.setBeamPushUrisCallback(mFileUriCallback, this);
    }

    private class FileUriCallback implements NfcAdapter.CreateBeamUrisCallback {
        @Override
        public Uri[] createBeamUris(NfcEvent event) {
            return mFileUris;
        }
    }

    public void handleViewIntent() {
        mIntent = getIntent();
        String action = mIntent.getAction();
        if (TextUtils.equals(action, Intent.ACTION_VIEW)) {
            Uri beamUri = mIntent.getData();
            if (TextUtils.equals(beamUri.getScheme(), "file")) {
                mParentPath = handleFileUri(beamUri);
                Log.d(TAG, "handleViewIntent: "+mParentPath);
            } else if (TextUtils.equals(
                    beamUri.getScheme(), "content")) {
                mParentPath = handleContentUri(beamUri);
                Log.d(TAG, "handleViewIntent: "+mParentPath);
            }
        }
    }

    public File handleFileUri(Uri beamUri) {
        // Get the path part of the URI
        String fileName = beamUri.getPath();
        // Create a File object for this filename
        File copiedFile = new File(fileName);
        // Get a string containing the file's parent directory
        return new File(copiedFile.getParent());
    }

    public File handleContentUri(Uri beamUri) {
        // Position of the filename in the query Cursor
        int filenameIndex;
        // File object for the filename
        File copiedFile;
        // The filename stored in MediaStore
        String fileName;
        // Test the authority of the URI
        if (!TextUtils.equals(beamUri.getAuthority(), MediaStore.AUTHORITY)) {
            /*
             * Handle content URIs for other content providers
             */
            // For a MediaStore content URI
        } else {
            // Get the column that contains the file name
            String[] projection = { MediaStore.MediaColumns.DATA };
            Cursor pathCursor =
                    getContentResolver().query(beamUri, projection,
                            null, null, null);
            // Check for a valid cursor
            if (pathCursor != null &&
                    pathCursor.moveToFirst()) {
                // Get the column index in the Cursor
                filenameIndex = pathCursor.getColumnIndex(
                        MediaStore.MediaColumns.DATA);
                // Get the full file name including path
                fileName = pathCursor.getString(filenameIndex);
                // Create a File object for the filename
                copiedFile = new File(fileName);
                // Return the parent directory of the file
                return new File(copiedFile.getParent());
            } else {
                // The query didn't work; return null
                return null;
            }
        }

        return null;
    }


}
