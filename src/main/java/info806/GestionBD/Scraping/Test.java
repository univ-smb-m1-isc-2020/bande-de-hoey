package info806.GestionBD.Scraping;

import org.json.JSONException;
import org.json.JSONObject;

public class Test {
    String before = "{\"includeFacets\":false,\"advancedQuery\":{\"limitClause\":\"ZMAT:\\\"bande_dessinees\\\" languageLimit_s:\\\"fre\\\"\",\"pageSize\":10,\"searchContext\":\"advancedsearch\",\"searchType\":\"all\",\"section\":\"*\",\"sort\":\"score\",\"terms\":[]},\"order\":\"score\",\"queryid\":\"fb33204c-acff-4451-98c0-2b5847179e5d\",\"sf\":\"*\",\"mappedFQ\":{},\"pageNo\":";
    String after =   ",\"pageSize\":8,\"locale\":\"fr\"}";
    JSONObject finalbf;

    Test(int  i) throws JSONException {
        String foo = this.before+i+this.after;
        this.finalbf =new JSONObject(foo);
    }
}
