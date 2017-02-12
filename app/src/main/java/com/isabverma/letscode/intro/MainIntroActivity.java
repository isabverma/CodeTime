package com.isabverma.letscode.intro;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.isabverma.letscode.R;
import com.isabverma.letscode.intro.app.IntroActivity;
import com.isabverma.letscode.intro.app.OnNavigationBlockedListener;
import com.isabverma.letscode.intro.slide.SimpleSlide;
import com.isabverma.letscode.intro.slide.Slide;


public class MainIntroActivity extends IntroActivity{

    private static final int RC_SIGN_IN = 9001;

    private FirebaseAuth mAuth;

    private Slide loginSlide;

    public static final String EXTRA_FULLSCREEN = "com.isabverma.letscode.intro.EXTRA_FULLSCREEN";
    public static final String EXTRA_SCROLLABLE = "com.isabverma.letscode.intro.EXTRA_SCROLLABLE";
    public static final String EXTRA_CUSTOM_FRAGMENTS = "com.isabverma.letscode.intro.EXTRA_CUSTOM_FRAGMENTS";
    public static final String EXTRA_PERMISSIONS = "com.isabverma.letscode.intro.EXTRA_PERMISSIONS";
    public static final String EXTRA_SHOW_BACK = "com.isabverma.letscode.intro.EXTRA_SHOW_BACK";
    public static final String EXTRA_SHOW_NEXT = "com.isabverma.letscode.intro.EXTRA_SHOW_NEXT";
    public static final String EXTRA_SKIP_ENABLED = "com.isabverma.letscode.intro.EXTRA_SKIP_ENABLED";
    public static final String EXTRA_FINISH_ENABLED = "com.isabverma.letscode.intro.EXTRA_FINISH_ENABLED";
    public static final String EXTRA_GET_STARTED_ENABLED = "com.isabverma.letscode.intro.EXTRA_GET_STARTED_ENABLED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();

        boolean fullscreen = intent.getBooleanExtra(EXTRA_FULLSCREEN, false);
        boolean scrollable = intent.getBooleanExtra(EXTRA_SCROLLABLE, false);
        boolean customFragments = intent.getBooleanExtra(EXTRA_CUSTOM_FRAGMENTS, true);
        boolean permissions = intent.getBooleanExtra(EXTRA_PERMISSIONS, true);
        boolean showBack = intent.getBooleanExtra(EXTRA_SHOW_BACK, true);
        boolean showNext = intent.getBooleanExtra(EXTRA_SHOW_NEXT, true);
        boolean skipEnabled = intent.getBooleanExtra(EXTRA_SKIP_ENABLED, true);
        boolean finishEnabled = intent.getBooleanExtra(EXTRA_FINISH_ENABLED, true);
        boolean getStartedEnabled = intent.getBooleanExtra(EXTRA_GET_STARTED_ENABLED, false);

        setFullscreen(fullscreen);

        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            customFragments = false;
        } else {
            customFragments = true;
        }

        setButtonBackFunction(skipEnabled ? BUTTON_BACK_FUNCTION_SKIP : BUTTON_BACK_FUNCTION_BACK);
        setButtonNextFunction(finishEnabled ? BUTTON_NEXT_FUNCTION_NEXT_FINISH : BUTTON_NEXT_FUNCTION_NEXT);
        setButtonBackVisible(showBack);
        setButtonNextVisible(showNext);
        setButtonCtaVisible(getStartedEnabled);
        setButtonCtaTintMode(BUTTON_CTA_TINT_MODE_TEXT);

        addSlide(new SimpleSlide.Builder()
                .title(R.string.slide_1_title)
                .description(R.string.slide_1_desc)
                .image(R.drawable.ic_food)
                .background(R.color.bg_screen1)
                .backgroundDark(R.color.color_dark_material_metaphor)
                .scrollable(scrollable)
                .build());

        addSlide(new SimpleSlide.Builder()
                .title(R.string.title_material_bold)
                .description(R.string.description_material_bold)
                .image(R.drawable.art_material_bold)
                .background(R.color.color_material_bold)
                .backgroundDark(R.color.color_dark_material_bold)
                .scrollable(scrollable)
                .buttonCtaLabel("Hello")
                .build());

        addSlide(new SimpleSlide.Builder()
                .title(R.string.title_material_motion)
                .description(R.string.description_material_motion)
                .image(R.drawable.art_material_motion)
                .background(R.color.color_material_motion)
                .backgroundDark(R.color.color_dark_material_motion)
                .scrollable(scrollable)
                .build());

        addSlide(new SimpleSlide.Builder()
                .title(R.string.title_material_shadow)
                .description(R.string.description_material_shadow)
                .image(R.drawable.art_material_shadow)
                .background(R.color.color_material_shadow)
                .backgroundDark(R.color.color_dark_material_shadow)
                .scrollable(scrollable)
                .build());

        final Slide permissionsSlide;
        int permissionCheck = ContextCompat.checkSelfPermission(MainIntroActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
       if(permissionCheck==0){
            permissionsSlide = null;
            removeSlide(permissionsSlide);
        }else {
            permissionsSlide = new SimpleSlide.Builder()
                    .title(R.string.title_permissions)
                    .description(R.string.description_permissions)
                    .background(R.color.color_permissions)
                    .backgroundDark(R.color.color_dark_permissions)
                    .scrollable(scrollable)
                    .permissions(new String[]{Manifest.permission.INTERNET,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE})
                    .build();
            addSlide(permissionsSlide);
        }

        if (customFragments) {
            loginSlide = new SimpleSlide.Builder()
                    .title("title login")
                    .description("description login")
                    .background(R.color.color_permissions)
                    .backgroundDark(R.color.color_dark_permissions)
                    .scrollable(scrollable)
                    .buttonCtaLabel("Sign In please")
                    .buttonCtaClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startSignIn();
                        }
                    })
                    .build();
            addSlide(loginSlide);
        } else {
            loginSlide = null;
        }

        addOnNavigationBlockedListener(new OnNavigationBlockedListener() {
            @Override
            public void onNavigationBlocked(int position, int direction) {
                View contentView = findViewById(android.R.id.content);
                if (contentView != null) {
                    Slide slide = getSlide(position);

                    if (slide == permissionsSlide) {
                        Snackbar.make(contentView, R.string.label_grant_permissions, Snackbar.LENGTH_LONG).show();
                    } else if (slide == loginSlide) {
                        Snackbar.make(contentView, R.string.label_fill_out_form, Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                // Sign in succeeded
                Toast.makeText(this, "Signed in Successful", Toast.LENGTH_SHORT).show();
                removeSlide(loginSlide);

            } else {
                // Sign in failed
                Toast.makeText(this, "Sign In Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void startSignIn() {
        // Build FirebaseUI sign in intent. For documentation on this operation and all
        // possible customization see: https://github.com/firebase/firebaseui-android
        Intent intent = AuthUI.getInstance().createSignInIntentBuilder()
                //.setIsSmartLockEnabled(!BuildConfig.DEBUG)
                .setProviders(AuthUI.EMAIL_PROVIDER)
                .setLogo(R.mipmap.ic_launcher)
                .build();

        startActivityForResult(intent, RC_SIGN_IN);
    }

}
