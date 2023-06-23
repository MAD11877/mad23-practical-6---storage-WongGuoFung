package sg.edu.np.mad.madpractical;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    ImageView image;
    TextView txt;
    TextView idtxt;
    public ViewHolder(View itemView){
        super(itemView);
        image = itemView.findViewById(R.id.userImageView);
        txt = itemView.findViewById(R.id.nameView);
        idtxt = itemView.findViewById(R.id.IDView);
    }

}