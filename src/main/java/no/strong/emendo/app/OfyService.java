package no.strong.emendo.app;

import no.strong.emendo.data.Page;
import no.strong.emendo.data.Site;
import no.strong.emendo.data.User;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

public class OfyService {
    static {
        factory().register(Site.class);
        factory().register(Page.class);
        factory().register(User.class);
    }

    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }
}