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
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.onoo.gomlgy.Network.response.AddToCartResponse;
import com.onoo.gomlgy.Network.response.AddToWishlistResponse;
import com.onoo.gomlgy.Network.response.AppSettingsResponse;
import com.onoo.gomlgy.Network.response.AuthResponse;
import com.onoo.gomlgy.Network.response.CheckWishlistResponse;
import com.onoo.gomlgy.Network.response.RemoveWishlistResponse;
import com.onoo.gomlgy.Network.response.VariantResponse;
import com.onoo.gomlgy.Presentation.presenters.ProductDetailsPresenter;
import com.onoo.gomlgy.Presentation.ui.activities.ProductDetailsView;
import com.onoo.gomlgy.Presentation.ui.adapters.BestSellingofSellerAdapter;
import com.onoo.gomlgy.Presentation.ui.adapters.ProductsAdapter;
import com.onoo.gomlgy.Presentation.ui.customui.MyRadioButton;
import com.onoo.gomlgy.Presentation.ui.listeners.ProductClickListener;
import com.onoo.gomlgy.Presentation.ui.listeners.ProductModelsClickListener;
import com.onoo.gomlgy.R;
import com.onoo.gomlgy.Threading.MainThreadImpl;
import com.onoo.gomlgy.Utils.AppConfig;
import com.onoo.gomlgy.Utils.CustomToast;
import com.onoo.gomlgy.Utils.UserPrefs;
import com.onoo.gomlgy.domain.executor.impl.ThreadExecutor;
import com.onoo.gomlgy.models.ChoiceOption;
import com.onoo.gomlgy.models.Product;
import com.onoo.gomlgy.models.ProductDetails3.ProductDetails3;
import com.thekhaeng.recyclerviewmargin.LayoutMarginDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.onoo.gomlgy.Utils.AppConfig.convertDpToPx;
import static com.onoo.gomlgy.Utils.AppConfig.mapResponse;

public class ProductDetailsActivity extends BaseActivity implements ProductDetailsView, ProductClickListener, ProductModelsClickListener {
    private String product_name, link, top_selling_link;
    private SliderLayout sliderLayout;
    private TextView name, subPriceTv;
    private RatingBar ratingBar;
    private TextView rating_count, price_1, price_2, price_3, quntity_1, quntity_2, quntity_3;
    private ImageView shop_logo, heart_icon;
    private TextView shop_name;
    private RelativeLayout buying_option, specification, reviews, seller_policy, return_policy, support_policy;
    private RecyclerView related_products, top_selling;
    private NestedScrollView product_details;
    private LinearLayout product_buttons;
    private ProgressBar progress_bar;
    private Button addTocart, buyNow;
    private ProductDetails3 productDetails = null;
    private ProgressDialog progressDialog;
    private AuthResponse authResponse;
    private ProductDetailsPresenter productDetailsPresenter;
    private CardView shop_info, image_card;
    private boolean isBuyNow = false;
    private RecyclerView modelRv;
    private RecyclerView productsModelsRv;

    List<String> productModelsList = new ArrayList<>();
    private HashMap<String, String> maps = new HashMap<>();
    private HashMap<String, String> selectedChoices = new HashMap<>();

    ImageView qtyIncrease, qtyDecrease;
    EditText quantity;
    int subPrice = 0;
    int getQuantity = 1;


    RadioGroup dynamicRadiogroup;
    LinearLayout linearLayout;
    MyRadioButton radioButton;
    LayoutInflater inflater;
    private TextView availableLbl, available;

    private static final String TAG = "ProductDetailss";
    String selectedColor = null;
    JsonArray choicesArray = null;
    int productId;
    private VariantResponse variantResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        product_name = getIntent().getStringExtra("product_name");
        link = getIntent().getStringExtra("link");
        top_selling_link = getIntent().getStringExtra("top_selling");

        initializeActionBar();
        setTitle(product_name);
        initviews();

        progress_bar.setVisibility(View.VISIBLE);
        product_details.setVisibility(View.INVISIBLE);
        product_buttons.setVisibility(View.INVISIBLE);
        image_card.setVisibility(View.GONE);

        productDetailsPresenter = new ProductDetailsPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);

        productDetailsPresenter.getProductDetails(link);

        seller_policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PolicyViewActivity.class);
                intent.putExtra("title", "Seller Policy");
                intent.putExtra("url", "policies/seller");
                startActivity(intent);
            }
        });

        specification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProductDescriptionActivity.class);
                intent.putExtra("product_name", productDetails.getName());
                intent.putExtra("description", "productDetails.getDescription()");
                startActivity(intent);
            }
        });

//        description.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), PolicyViewActivity.class);
//                intent.putExtra("url", "http://shop.activeitzone.com/privacypolicy");
//                startActivity(intent);
//            }
//        });

        reviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetailsActivity.this, ProductReviewActivity.class);
//                intent.putExtra("url", productDetails.getLinks().getReviews());
                startActivity(intent);
            }
        });

        return_policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PolicyViewActivity.class);
                intent.putExtra("title", "Seller Policy");
                intent.putExtra("url", "policies/return");
                startActivity(intent);
            }
        });

        support_policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PolicyViewActivity.class);
                intent.putExtra("title", "Seller Policy");
                intent.putExtra("url", "policies/support");
                startActivity(intent);
            }
        });


        buying_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startBuyingOptionActivity();
            }
        });

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


        qtyIncrease.setOnClickListener(v -> {

            int qty = Integer.parseInt(quantity.getText().toString()) + 1;
            quantity.setText(String.valueOf(qty));
            qtyDecrease.setEnabled(true);
            productDetailsPresenter.getVariantPrice(productId, selectedColor, choicesArray, qty);
            Log.i(TAG, "iddddd " + productId);
        });

        qtyDecrease.setOnClickListener(v -> {
            int qty = Integer.parseInt(quantity.getText().toString());

            if (qty > 1) {
                if (qty == 1) {
                    qtyDecrease.setEnabled(false);
                    return;
                }
                Log.i(TAG, " : " + choicesArray.size());
                quantity.setText(String.valueOf(qty - 1));
                qtyDecrease.setEnabled(true);
                productDetailsPresenter.getVariantPrice(productId, selectedColor, choicesArray, qty);
            }
        });
    }

    void setBuyingOption(ProductDetails3 productDetails) {
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        linearLayout = (LinearLayout) findViewById(R.id.test);

        for (ChoiceOption choiceOption : productDetails.getChoiceOptions()) {
            dynamicRadiogroup = new RadioGroup(ProductDetailsActivity.this);
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


        if (productDetails.getColors().size() > 0) {
            TextView textview = new TextView(this);
            textview.setText("Colors");
            textview.setBackgroundColor(Color.parseColor("#F1F1F5"));
            textview.setTextColor(Color.BLACK);
            textview.setPadding(16, 25, 0, 25);
            textview.setTextSize(20);
            linearLayout.addView(textview);
            dynamicRadiogroup = new RadioGroup(ProductDetailsActivity.this);
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
                    selectedColor = radiochecked.getText().toString();
                    createSelectedChoiceList(radiochecked.getText().toString());
                }
            });
        }
    }

    private void processAddToCart() {
        getQuantity = Integer.parseInt(quantity.getText().toString());
        if (productDetails != null && (productDetails.getChoiceOptions().size() > 0 || productDetails.getColors().size() > 0)) {
            if (selectedColor != null) {
                if (choicesArray.size() > 0) {
                    if (variantResponse != null && variantResponse.getInStock()) {
                        progressDialog.setMessage(getString(R.string.adding_item_to_your_shopping_cart_please_wait));
                        progressDialog.show();
                        productDetailsPresenter.addToCart(authResponse.getAccessToken(), authResponse.getUser().getId(), productDetails.getId(), variantResponse.getVariant(), getQuantity);
                    }
                } else {
                    Toast.makeText(this, "please select model", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "please select Color", Toast.LENGTH_SHORT).show();
            }
//            startBuyingOptionActivity();
        } else {
            AuthResponse authResponse = new UserPrefs(getApplicationContext()).getAuthPreferenceObjectJson("auth_response");
            if (authResponse != null && authResponse.getUser() != null) {
                progressDialog.setMessage(getString(R.string.adding_item_to_your_shopping_cart_please_wait));
                progressDialog.show();
                productDetailsPresenter.addToCart(authResponse.getAccessToken(), authResponse.getUser().getId(), productDetails.getId(), null, getQuantity);
            } else {
                startActivityForResult(new Intent(getApplicationContext(), LoginActivity.class), 100);
                finish();
            }
        }
    }

    private void startBuyingOptionActivity() {
        if (productDetails != null && (productDetails.getChoiceOptions().size() > 0 || productDetails.getColors().size() > 0)) {
            Intent intent = new Intent(getApplicationContext(), BuyingOptionsActivity.class);
            intent.putExtra("product_details", productDetails);
            intent.putExtra("qunatity", getQuantity);

            startActivity(intent);
        } else {
            CustomToast.showToast(this, getString(R.string.this_product_doesnt_have_any_buying_options), R.color.colorWarning);
        }
    }

    private void initviews() {
        product_details = findViewById(R.id.product_details);
        product_buttons = findViewById(R.id.product_buttons);
        progress_bar = findViewById(R.id.item_progress_bar);
        sliderLayout = findViewById(R.id.imageSlider);
        sliderLayout.stopAutoCycle();
        name = findViewById(R.id.product_name);
        ratingBar = findViewById(R.id.product_rating);
        rating_count = findViewById(R.id.product_rating_count);
        price_1 = findViewById(R.id.product_price_1);
        price_2 = findViewById(R.id.product_price_2);
        price_3 = findViewById(R.id.product_price_3);
        quntity_1 = findViewById(R.id.product_quntity_1);
        quntity_2 = findViewById(R.id.product_quntity_2);
        quntity_3 = findViewById(R.id.product_quntity_3);

        shop_logo = findViewById(R.id.shop_logo);
        heart_icon = findViewById(R.id.heart_icon);
        shop_name = findViewById(R.id.shop_name);
        buying_option = findViewById(R.id.buying_option);
        specification = findViewById(R.id.specification);
        //description = findViewById(R.id.description);
        reviews = findViewById(R.id.reviews);
        seller_policy = findViewById(R.id.seller_policy);
        return_policy = findViewById(R.id.return_policy);
        support_policy = findViewById(R.id.support_policy);
        related_products = findViewById(R.id.related_products);
        top_selling = findViewById(R.id.top_selling);

        addTocart = findViewById(R.id.addToCart);
        buyNow = findViewById(R.id.buyNow);

        progressDialog = new ProgressDialog(this);
        shop_info = findViewById(R.id.shop_info);
        image_card = findViewById(R.id.image_card);

        productsModelsRv = findViewById(R.id.product_models_rv);
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.FLEX_END);
        productsModelsRv.setLayoutManager(layoutManager);
        subPriceTv = findViewById(R.id.price_tv);
        qtyIncrease = findViewById(R.id.cart_quantity_increase);
        qtyDecrease = findViewById(R.id.cart_quantity_decrease);
        quantity = findViewById(R.id.cart_quantity);

        available = findViewById(R.id.product_available_tv);
        availableLbl = findViewById(R.id.product_available_lbl_tv);
    }

    @Override
    public void setProductDetails(ProductDetails3 productDetails) {
        this.productDetails = productDetails;

        productId = productDetails.getId();
        for (String photo : productDetails.getPhotos()) {
            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView
                    .description("")
                    .image(AppConfig.ASSET_URL + photo)
                    .setScaleType(BaseSliderView.ScaleType.CenterInside);
            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Default);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        image_card.setVisibility(View.VISIBLE);

        name.setText(productDetails.getName());
        ratingBar.setRating(productDetails.getRating());
        rating_count.setText("(" + productDetails.getRatingCount() + ")");

        authResponse = new UserPrefs(getApplicationContext()).getAuthPreferenceObjectJson("auth_response");
        if (authResponse != null && authResponse.getUser() != null) {
            productDetailsPresenter.checkOnWishlist(authResponse.getAccessToken(), authResponse.getUser().getId(), productDetails.getId());
        } else {
            heart_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivityForResult(new Intent(getApplicationContext(), LoginActivity.class), 100);
                    finish();
                }
            });
        }

        if (!productDetails.getAddedBy().equals("admin")) {
            Glide.with(this).load(AppConfig.ASSET_URL + productDetails.getUser().getShopLogo()).into(shop_logo);
            shop_name.setText(productDetails.getUser().getShopName());
            shop_info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ProductDetailsActivity.this, SellerShopActivity.class);
                    intent.putExtra("shop_name", productDetails.getUser().getShopName());
                    intent.putExtra("shop_link", productDetails.getUser().getShopLink());
                    startActivity(intent);
                }
            });
        } else {
            AppSettingsResponse appSettingsResponse = new UserPrefs(this).getAppSettingsPreferenceObjectJson("app_settings_response");
            if (appSettingsResponse != null) {
                Glide.with(this).load(AppConfig.ASSET_URL + appSettingsResponse.getData().get(0).getLogo()).into(shop_logo);
            }
        }

        price_1.setText(AppConfig.convertPrice2(this, Double.valueOf(productDetails.getUnitPrice())));
        price_2.setText(AppConfig.convertPrice2(this, Double.valueOf(productDetails.getUnitPrice2())));
        price_3.setText(AppConfig.convertPrice2(this, Double.valueOf(productDetails.getUnitPrice3())));

        quntity_1.setText(productDetails.getMinQuantity1() + " - " + productDetails.getMaxQuantity1() + " " + getString(R.string.piece));
        quntity_2.setText(productDetails.getMinQuantity2() + " - " + productDetails.getMaxQuantity2() + " " + getString(R.string.piece));
        quntity_3.setText(productDetails.getMinQuantity3() + " - " + productDetails.getMaxQuantity3() + " " + getString(R.string.piece));

        progress_bar.setVisibility(View.GONE);
        product_details.setVisibility(View.VISIBLE);
        product_buttons.setVisibility(View.VISIBLE);

        subPriceTv.setText(AppConfig.convertPrice2(this, Double.valueOf(productDetails.getUnitPrice())));

        new ProductDetailsPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this).getRelatedProducts(productDetails.getLinks().getRelated());

        new ProductDetailsPresenter(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this).getTopSellingProducts(top_selling_link);

        setBuyingOption(productDetails);
    }

    @Override
    public void setRelatedProducts(List<Product> relatedProducts) {
        RecyclerView recyclerView = findViewById(R.id.related_products);
        GridLayoutManager horizontalLayoutManager = new
                GridLayoutManager(this, 1, GridLayoutManager.HORIZONTAL,
                false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        ProductsAdapter adapter = new ProductsAdapter(this, mapResponse(relatedProducts),
                this);
        adapter.setViewType(1);
        recyclerView.addItemDecoration(new LayoutMarginDecoration(1, convertDpToPx(getApplicationContext(), 10)));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setTopSellingProducts(List<Product> topSellingProducts) {
        RecyclerView recyclerView = findViewById(R.id.top_selling);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        ProductsAdapter adapter = new ProductsAdapter(this, mapResponse(topSellingProducts), this);
        Log.i("ccccc: ", "ccccc" + topSellingProducts.get(0).getThumbnailImage());
        adapter.setViewType(3);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setAddToCartMessage(AddToCartResponse addToCartResponse) {
        progressDialog.dismiss();
        if (isBuyNow) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("message", addToCartResponse.getMessage());
            intent.putExtra("position", "cart");
            startActivity(intent);
            finish();
        } else {
            CustomToast.showToast(this, addToCartResponse.getMessage(), R.color.colorSuccess);
        }
    }

    @Override
    public void setAddToWishlistMessage(AddToWishlistResponse addToWishlistMessage) {
        CustomToast.showToast(this, addToWishlistMessage.getMessage(), R.color.colorSuccess);
        heart_icon.setImageResource(R.drawable.ic_heart_filled);
        productDetailsPresenter.checkOnWishlist(authResponse.getAccessToken(), authResponse.getUser().getId(), productDetails.getId());
    }

    @Override
    public void onCheckWishlist(CheckWishlistResponse checkWishlistResponse) {
        if (checkWishlistResponse.getIsInWishlist()) {
            heart_icon.setImageResource(R.drawable.ic_heart_filled);
            heart_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    productDetailsPresenter.removeFromWishlist(authResponse.getAccessToken(), checkWishlistResponse.getWishlistId());
                }
            });
        } else {
            heart_icon.setImageResource(R.drawable.ic_heart);
            heart_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    productDetailsPresenter.addToWishlist(authResponse.getAccessToken(), authResponse.getUser().getId(), productDetails.getId());
                }
            });
        }
    }

    @Override
    public void onRemoveFromWishlist(RemoveWishlistResponse removeWishlistResponse) {
        heart_icon.setImageResource(R.drawable.ic_heart);
        CustomToast.showToast(this, removeWishlistResponse.getMessage(), R.color.colorSuccess);
        productDetailsPresenter.checkOnWishlist(authResponse.getAccessToken(), authResponse.getUser().getId(), productDetails.getId());
    }

    @Override
    public void setVariantprice(VariantResponse variantResponse) {
        if (variantResponse != null) {
            this.variantResponse = variantResponse;
            subPriceTv.setText(AppConfig.convertPrice2(ProductDetailsActivity.this, ((double) (variantResponse.getPrice() * (Integer.parseInt(quantity.getText().toString()))))));
            availableLbl.setVisibility(View.VISIBLE);
            available.setVisibility(View.VISIBLE);
            available.setText(variantResponse.getQuantity() + "");
        }
    }

    @Override
    public void onProductItemClick(Product product) {
        Intent intent = new Intent(this, ProductDetailsActivity.class);
        intent.putExtra("product_name", product.getName());
        intent.putExtra("link", product.getLinks().getDetails());
        intent.putExtra("top_selling", product.getLinks().getRelated());
        startActivity(intent);
    }

    @Override
    public void onModelsItemClick(String option) {
        createSelectedChoiceList(option);
    }

    private void createSelectedChoiceList(String value) {
        choicesArray = new JsonArray();

        String choiceName = maps.get(value);
        if (!choiceName.equals("color")) {
            selectedChoices.put(choiceName, value);
        } else {
            selectedChoices.put("color", value);
        }

        int id = productDetails.getId();
        String color = selectedChoices.get("color") != null ? selectedChoices.get("color") : null;

        for (ChoiceOption choiceOption : productDetails.getChoiceOptions()) {
            if (selectedChoices.get(choiceOption.getName()) != null) {
                JsonObject choice = new JsonObject();
                choice.addProperty("section", choiceOption.getName());
                choice.addProperty("title", choiceOption.getTitle());
                choice.addProperty("name", selectedChoices.get(choiceOption.getName()));
                choicesArray.add(choice);
            }
        }

        Log.i(TAG, "choicesArray   " + choicesArray.toString());
        productDetailsPresenter.getVariantPrice(id, color, choicesArray, Integer.parseInt(quantity.getText().toString()));
    }
}