package com.onoo.gomlgy.Presentation.ui.activities.impl;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.onoo.gomlgy.Network.response.AddToCartResponse;
import com.onoo.gomlgy.Network.response.AuthResponse;
import com.onoo.gomlgy.Network.response.VariantResponse;
import com.onoo.gomlgy.Presentation.presenters.BuyingOptionPresenter;
import com.onoo.gomlgy.Presentation.ui.activities.BuyingOptionView;
import com.onoo.gomlgy.Presentation.ui.customui.MyRadioButton;
import com.onoo.gomlgy.R;
import com.onoo.gomlgy.Threading.MainThreadImpl;
import com.onoo.gomlgy.Utils.AppConfig;
import com.onoo.gomlgy.Utils.CustomToast;
import com.onoo.gomlgy.Utils.UserPrefs;
import com.onoo.gomlgy.domain.executor.impl.ThreadExecutor;
import com.onoo.gomlgy.models.ChoiceOption;
import com.onoo.gomlgy.models.ProductDetails3.ProductDetails3;

import java.util.HashMap;

public class BuyingOptionsActivity extends BaseActivity implements BuyingOptionView {
    RadioGroup dynamicRadiogroup;
    LinearLayout linearLayout;
    MyRadioButton radioButton;
    ProductDetails3 productDetails;
    private TextView name;
    private RatingBar ratingBar;
    private TextView price;
    private TextView availableLbl, available,availble_items;
    private ImageView product_image;
    private HashMap<String, String> maps = new HashMap<>();
    private HashMap<String, String> selectedChoices = new HashMap<>();
    private Button addTocart, buyNow;
    private VariantResponse variantResponse;
    private BuyingOptionPresenter buyingOptionPresenter;
    private ProgressDialog progressDialog;
    LayoutInflater inflater;
    private boolean isBuyNow = false;
    private static final String TAG = "BuyingOptionsActivity";
    int quantity;
    private void initviews() {
        product_image = findViewById(R.id.product_image);
        name = findViewById(R.id.product_name);
        price = findViewById(R.id.product_price);
        available = findViewById(R.id.product_available_tv);
        availableLbl = findViewById(R.id.product_available_lbl_tv);
        ratingBar = findViewById(R.id.product_rating);
        addTocart = findViewById(R.id.addToCart);
        buyNow = findViewById(R.id.buyNow);
        buyingOptionPresenter = new BuyingOptionPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        progressDialog = new ProgressDialog(this);
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    private void createSelectedChoiceList(String value) {
        String choiceName = maps.get(value);
        if (!choiceName.equals("color")) {
            selectedChoices.put(choiceName, value);
        } else {
            selectedChoices.put("color", value);
        }

        int id = productDetails.getId();
        String color = selectedChoices.get("color") != null ? selectedChoices.get("color") : null;
        JsonArray choicesArray = new JsonArray();

        for (ChoiceOption choiceOption : productDetails.getChoiceOptions()) {
            if (selectedChoices.get(choiceOption.getName()) != null) {
                JsonObject choice = new JsonObject();
                choice.addProperty("section", choiceOption.getName());
                choice.addProperty("title", choiceOption.getTitle());
                choice.addProperty("name", selectedChoices.get(choiceOption.getName()));
                choicesArray.add(choice);
            }
        }

        //Log.d("Test", choicesArray.toString());

        buyingOptionPresenter.getVariantPrice(id, color, choicesArray,quantity);
    }
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buying_options);
        initviews();
        productDetails = (ProductDetails3) getIntent().getSerializableExtra("product_details");
        quantity = getIntent().getIntExtra("qunatity", 1);
        initializeActionBar();
        setTitle("Buying Options");
        Glide.with(this).load(AppConfig.ASSET_URL + productDetails.getThumbnailImage()).into(product_image);
        name.setText(productDetails.getName());
        ratingBar.setRating(productDetails.getRating());
        linearLayout = (LinearLayout) findViewById(R.id.test);
        for (ChoiceOption choiceOption : productDetails.getChoiceOptions()) {
            dynamicRadiogroup = new RadioGroup(BuyingOptionsActivity.this);
            LinearLayout.LayoutParams layoutparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutparams.setMargins(0, 0, 16, 0);
            dynamicRadiogroup.setLayoutParams(layoutparams);
            for (String optionvalue : choiceOption.getOptions()) {
                radioButton = (MyRadioButton) inflater.inflate(R.layout.my_radio_button, null);
                radioButton.setText(optionvalue);
                radioButton.setLayoutParams(layoutparams);
                radioButton.setTextSize(16);
                maps.put(optionvalue, choiceOption.getName());
                dynamicRadiogroup.addView(radioButton);
            }

            TextView textview = new TextView(this);
            textview.setText(choiceOption.getTitle());
            textview.setBackgroundColor(Color.parseColor("#F1F1F5"));
            textview.setTextColor(Color.BLACK);
            textview.setPadding(16, 25, 0, 25);
            textview.setTextSize(20);

            linearLayout.addView(textview);
            linearLayout.addView(dynamicRadiogroup);

            dynamicRadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                @SuppressLint("ResourceType")
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    // TODO Auto-generated method stub
                    MyRadioButton radiochecked = (MyRadioButton) findViewById(checkedId);
                    createSelectedChoiceList(radiochecked.getText().toString());
                }
            });
        }

        //adding color radio buttons

        if (productDetails.getColors().size() > 0) {
            TextView textview = new TextView(this);
            textview.setText("Colors");
            textview.setBackgroundColor(Color.parseColor("#F1F1F5"));
            textview.setTextColor(Color.BLACK);
            textview.setPadding(16, 25, 0, 25);
            textview.setTextSize(20);
            linearLayout.addView(textview);
            dynamicRadiogroup = new RadioGroup(BuyingOptionsActivity.this);
            LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dynamicRadiogroup.setOrientation(LinearLayout.HORIZONTAL);
            dynamicRadiogroup.setLayoutParams(linearParams);

            //Radio Button params
            LinearLayout.LayoutParams layoutparams = new LinearLayout.LayoutParams
                    (convertDpToPx(this, 50), convertDpToPx(this, 50));
            layoutparams.setMargins(16, 16, 16, 16);

            for (String color : productDetails.getColors()) {
                radioButton = (MyRadioButton) inflater.inflate(R.layout.color_radio_button, null);
                StateListDrawable drawable = (StateListDrawable) radioButton.getBackground();
                DrawableContainer.DrawableContainerState dcs = (DrawableContainer.DrawableContainerState) drawable.getConstantState();
                Drawable[] drawableItems = dcs.getChildren();
                GradientDrawable gradientDrawableChecked = (GradientDrawable) drawableItems[0]; // item 1
                GradientDrawable gradientDrawableUnChecked = (GradientDrawable) drawableItems[1]; // item 2
                //solid color
                gradientDrawableChecked.setColor(Color.parseColor(color));
                gradientDrawableUnChecked.setColor(Color.parseColor(color));
                radioButton.setLayoutParams(layoutparams);
                radioButton.setText(color);
                radioButton.setTextColor(Color.parseColor(color));
                radioButton.setTextSize(0);
                maps.put(color, "color");
                dynamicRadiogroup.addView(radioButton);
            }


            HorizontalScrollView horizontalScrollView = new HorizontalScrollView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            horizontalScrollView.setLayoutParams(layoutParams);
            horizontalScrollView.setHorizontalScrollBarEnabled(false);

            horizontalScrollView.addView(dynamicRadiogroup);
            linearLayout.addView(horizontalScrollView);

            dynamicRadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                @SuppressLint("ResourceType")
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    // TODO Auto-generated method stub
                    MyRadioButton radiochecked = (MyRadioButton) findViewById(checkedId);
                    createSelectedChoiceList(radiochecked.getText().toString());
                }
            });
        }
        addTocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processAddToCart();
            }
        });
        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isBuyNow = true;
                processAddToCart();
            }
        });
    }
    private void processAddToCart() {
        AuthResponse authResponse = new UserPrefs(getApplicationContext()).getAuthPreferenceObjectJson("auth_response");
        if (authResponse != null && authResponse.getUser() != null) {
            if (variantResponse != null && variantResponse.getInStock()) {
                //Log.d("Test", variantResponse.getVariant());
                progressDialog.setMessage(getString(R.string.adding_item_to_your_shopping_cart_please_wait));
                progressDialog.show();
                buyingOptionPresenter.addToCart(authResponse.getAccessToken(), authResponse.getUser().getId(), variantResponse.getProductId(), variantResponse.getVariant(), quantity);
            } else {
                CustomToast.showToast(BuyingOptionsActivity.this, getString(R.string.this_variant_of_this_product_isnt_available_now), R.color.colorWarning);
            }
        } else {
            startActivityForResult(new Intent(getApplicationContext(), LoginActivity.class), 100);
            finish();
        }
    }

    @Override
    public void setVariantprice(VariantResponse variantResponse) {
       // Log.i(TAG, );
      //  Toast.makeText(this, "id : " + variantResponse.getProductId() + " color : " + variantResponse.getQuantity() + " choice : " , Toast.LENGTH_LONG).show();
        this.variantResponse = variantResponse;
        price.setText(AppConfig.convertPrice(this, Double.valueOf(variantResponse.getPrice())));
        availableLbl.setVisibility(View.VISIBLE);
        available.setVisibility(View.VISIBLE);
    //    available.setText(AppConfig.convertPrice(this, variantResponse.getQuantity()));
        available.setText(variantResponse.getQuantity()+"");

     //  availble_items.setText(variantResponse.getVariant());
    }

    @Override
    public void setAddToCartMessage(AddToCartResponse addToCartResponse) {
        progressDialog.dismiss();
        if (isBuyNow) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("message", addToCartResponse.getMessage());
            intent.putExtra("position", "cart");
            finish();
            startActivity(intent);
        } else {
            CustomToast.showToast(this, addToCartResponse.getMessage(), R.color.colorSuccess);
        }
    }

    public int convertDpToPx(Context context, float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }
}
