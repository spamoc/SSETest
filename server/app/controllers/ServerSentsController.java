package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class ServerSentsController extends Controller{

    public static Result userStream(){
        Chunks<String> chunk = new StringChunks(){
            @Override
            public void onReady(play.mvc.Results.Chunks.Out<String> out) {
                out.write("あ！");
                out.close();
            }
            
        };
        return ok(chunk);
    }
}
