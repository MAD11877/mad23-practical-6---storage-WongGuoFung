package sg.edu.np.mad.madpractical;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {
    ArrayList<User> userData;
    Context mContext;
    AlertDialog.Builder builder;
    public Adapter (Context context, ArrayList<User> input){
        userData = input;
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        User s = userData.get(position);
        if (s.getName().endsWith("7")) {
            return 0;
        }
        return 1;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==0){
            View item2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_user, parent, false);
            return new ViewHolder(item2);
        }
        View item1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler, parent, false);
        return new ViewHolder(item1);
    }

    public void onBindViewHolder(ViewHolder holder, int position){
        User s = userData.get(position);
        holder.txt.setText(s.getName());
        holder.idtxt.setText(s.getDescription());
        builder = new AlertDialog.Builder(mContext);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Profile");
                builder.setMessage(s.getName());
                builder.setPositiveButton(
                        "VIEW",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent myIntent = new Intent(mContext, MainActivity.class);
                                myIntent.putExtra("key", (CharSequence) s);
                                mContext.startActivity(myIntent);
                            }
                        });
                builder.setNegativeButton(
                        "Close",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                builder.show();
            }
        });
    }

    public int getItemCount(){
        return userData.size();
    }
}