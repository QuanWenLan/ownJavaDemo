package org.example.intro;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.File;
import java.util.List;
import java.util.Objects;

/**
 * @author Lan
 * @createTime 2023-12-01  15:32
 **/
public class RecommenderIntro {
    public static void main(String[] args) {
        try {
            DataModel model = new FileDataModel(new File(Objects.requireNonNull(RecommenderIntro.class.getClassLoader().getResource("intro.csv")).getPath()));
            UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
            NearestNUserNeighborhood neighborhood = new NearestNUserNeighborhood(2, similarity, model);
            // create Recommender engine
            Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
            // for user 1, recommend 1 item
            List<RecommendedItem> recommendations = recommender.recommend(1, 1);
            for (RecommendedItem recommendedItem : recommendations) {
                System.out.println("recommendedItem = " + recommendedItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
