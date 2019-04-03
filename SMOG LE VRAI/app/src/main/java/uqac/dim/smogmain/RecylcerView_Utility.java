package uqac.dim.smogmain;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecylcerView_Utility {
    private Context mContext;
    private FilmViewAdapter filmViewAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Film> films, List<String> keys) {
        mContext = context;
        filmViewAdapter = new FilmViewAdapter(films, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(filmViewAdapter);
    }

    class FilmView extends RecyclerView.ViewHolder {
        private TextView mTitle;
        private TextView mReal;
        private TextView mDate;
        private TextView mNote;

        private String key;

        public FilmView(ViewGroup parent) {
            super(LayoutInflater.from(mContext).inflate(R.layout.film_item, parent, false));

            mTitle = (TextView) itemView.findViewById(R.id.Title_Disp);
            mReal = (TextView) itemView.findViewById(R.id.Real_Disp);
            mDate = (TextView) itemView.findViewById(R.id.Date_Disp);
            mNote = (TextView) itemView.findViewById(R.id.Note_Disp);
        }

        public void bind(Film film, String key) {
            mTitle.setText(film.getTitle());
            mReal.setText(film.getReal());
            mDate.setText(film.getDate());
            mNote.setText(film.getNote());
            this.key = key;
        }
    }

    class FilmViewAdapter extends RecyclerView.Adapter<FilmView> {
        private List<Film> mfilmList;
        private List<String> mkeys;

        public FilmViewAdapter(List<Film> filmList, List<String> keys) {
            this.mfilmList = filmList;
            this.mkeys = keys;
        }

        @NonNull
        @Override
        public FilmView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new FilmView(viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull FilmView filmView, int i) {
            filmView.bind(mfilmList.get(i), mkeys.get(i));
        }

        @Override
        public int getItemCount() {
            return mfilmList.size();
        }
    }
}
