package com.gatevision.gatevision;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BulletSpan;
import android.text.style.StyleSpan;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Output extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_output);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        TextView OutputDetailsOfGate = findViewById(R.id.OutputDetailsOfGate); // Make sure to set the correct ID

        SpannableStringBuilder builder = new SpannableStringBuilder();

        // Add each bullet point
        addBulletPoint(builder, "Durability: Gates are designed to withstand various weather conditions, ensuring long-lasting performance and minimal maintenance.");
        addBulletPoint(builder, "Security: Provides enhanced security for properties, acting as a barrier against unauthorized access.");
        addBulletPoint(builder, "Aesthetic Appeal: Available in various designs, colors, and materials, enhancing the overall look of your property.");
        addBulletPoint(builder, "Customization: Can be tailored to fit specific sizes and styles, ensuring they meet your unique needs.");
        addBulletPoint(builder, "Ease of Use: Equipped with user-friendly mechanisms (manual or automatic) for convenient operation.");
        addBulletPoint(builder, "Versatility: Suitable for residential, commercial, and industrial applications, serving multiple purposes.");
        addBulletPoint(builder, "Material Options: Common materials include wood, metal, and vinyl, each offering distinct advantages in terms of style and maintenance.");
        addBulletPoint(builder, "Privacy: Solid gates can provide an added level of privacy for homes and yards.");
        addBulletPoint(builder, "Environmentally Friendly: Options available in sustainable materials and designs that minimize environmental impact.");

        OutputDetailsOfGate.setText(builder);

    }


    private void addBulletPoint(SpannableStringBuilder builder, String text) {
        int start = builder.length();
        builder.append(text + "\n"); // Append text with a newline
        int end = builder.length();

        // Add bullet span
        builder.setSpan(new BulletSpan(10), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Make the title part bold
        int titleEnd = start + text.indexOf(":") + 1; // Find the position of the colon
        builder.setSpan(new StyleSpan(Typeface.BOLD), start, titleEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
}