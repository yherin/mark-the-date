/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mtd.data.load;


/**
 *
 * @author sjack
 */
public final class EventLoader extends Loader {

    public EventLoader() {
        super();
    }

    @Override
    String filePathString() {
        String fp = "mtd/json/";
        return fp;
    }

    @Override
    String fileNameString() {
        String fn = "events.json";
        return fn;
    }

}
