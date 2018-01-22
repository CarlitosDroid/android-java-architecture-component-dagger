package com.example.carlos.ac_android.repository.remote.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Carlos Leonardo Camilo Vargas Huamán on 1/21/18.
 */

public class UserResponse {


    /**
     * id : 1
     * name : Human Made
     * url :
     * description :
     * link : https://demo.wp-api.org/author/humanmade/
     * slug : humanmade
     * avatar_urls : {"24":"http://2.gravatar.com/avatar/83888eb8aea456e4322577f96b4dbaab?s=24&d=mm&r=g","48":"http://2.gravatar.com/avatar/83888eb8aea456e4322577f96b4dbaab?s=48&d=mm&r=g","96":"http://2.gravatar.com/avatar/83888eb8aea456e4322577f96b4dbaab?s=96&d=mm&r=g"}
     * meta : []
     * _links : {"self":[{"href":"https://demo.wp-api.org/wp-json/wp/v2/users/1"}],"collection":[{"href":"https://demo.wp-api.org/wp-json/wp/v2/users"}]}
     */

    private int id;
    private String name;
    private String url;
    private String description;
    private String link;
    private String slug;
    private AvatarUrlsBean avatar_urls;
    private LinksBean _links;
    private List<?> meta;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public AvatarUrlsBean getAvatar_urls() {
        return avatar_urls;
    }

    public void setAvatar_urls(AvatarUrlsBean avatar_urls) {
        this.avatar_urls = avatar_urls;
    }

    public LinksBean get_links() {
        return _links;
    }

    public void set_links(LinksBean _links) {
        this._links = _links;
    }

    public List<?> getMeta() {
        return meta;
    }

    public void setMeta(List<?> meta) {
        this.meta = meta;
    }

    public static class AvatarUrlsBean {
        /**
         * 24 : http://2.gravatar.com/avatar/83888eb8aea456e4322577f96b4dbaab?s=24&d=mm&r=g
         * 48 : http://2.gravatar.com/avatar/83888eb8aea456e4322577f96b4dbaab?s=48&d=mm&r=g
         * 96 : http://2.gravatar.com/avatar/83888eb8aea456e4322577f96b4dbaab?s=96&d=mm&r=g
         */

        @SerializedName("24")
        private String _$24;
        @SerializedName("48")
        private String _$48;
        @SerializedName("96")
        private String _$96;

        public String get_$24() {
            return _$24;
        }

        public void set_$24(String _$24) {
            this._$24 = _$24;
        }

        public String get_$48() {
            return _$48;
        }

        public void set_$48(String _$48) {
            this._$48 = _$48;
        }

        public String get_$96() {
            return _$96;
        }

        public void set_$96(String _$96) {
            this._$96 = _$96;
        }
    }

    public static class LinksBean {
        private List<SelfBean> self;
        private List<CollectionBean> collection;

        public List<SelfBean> getSelf() {
            return self;
        }

        public void setSelf(List<SelfBean> self) {
            this.self = self;
        }

        public List<CollectionBean> getCollection() {
            return collection;
        }

        public void setCollection(List<CollectionBean> collection) {
            this.collection = collection;
        }

        public static class SelfBean {
            /**
             * href : https://demo.wp-api.org/wp-json/wp/v2/users/1
             */

            private String href;

            public String getHref() {
                return href;
            }

            public void setHref(String href) {
                this.href = href;
            }
        }

        public static class CollectionBean {
            /**
             * href : https://demo.wp-api.org/wp-json/wp/v2/users
             */

            private String href;

            public String getHref() {
                return href;
            }

            public void setHref(String href) {
                this.href = href;
            }
        }
    }
}
