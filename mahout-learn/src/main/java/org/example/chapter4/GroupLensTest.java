package org.example.chapter4;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.LoadEvaluator;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.cf.taste.similarity.precompute.example.GroupLensDataModel;

import java.io.File;
import java.io.IOException;

/**
 * @author Lan
 * @createTime 2023-12-07  22:27
 **/
public class GroupLensTest {
    public static void main(String[] args) {

    }

    public static void testDataModel() throws IOException, TasteException {
        DataModel dataModel = new GroupLensDataModel(new File("ratings.csv"));
        UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
        UserNeighborhood neighborhood = new NearestNUserNeighborhood(100, similarity, dataModel);
        Recommender recommender = new GenericUserBasedRecommender(dataModel, neighborhood, similarity);
        LoadEvaluator.runLoad(recommender);
    }

    public static void testUserNeighborhood() throws IOException, TasteException {
        DataModel dataModel = new GroupLensDataModel(new File("ratings.csv"));
        RecommenderEvaluator evaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();
        RecommenderBuilder recommenderBuilder = new RecommenderBuilder() {
            @Override
            public Recommender buildRecommender(DataModel dataModel) throws TasteException {
                UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
                UserNeighborhood neighborhood = new NearestNUserNeighborhood(100, similarity, dataModel);
                return new GenericUserBasedRecommender(dataModel, neighborhood, similarity);
            }
        };
        double score = evaluator.evaluate(recommenderBuilder, null, dataModel, 0.95, 0.05);
        System.out.println(score);
    }
}
