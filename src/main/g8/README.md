# Twitter Bootstrap

Bootstrap is included via a git submodule. After creating this project
you must run, in the project root directory:

    git init
    git submodule add https://github.com/twitter/bootstrap.git modules/bootstrap
    git submodule init
    git add modules/bootstrap
    cd modules/bootstrap
    git co v1.4.0

This will pull in the necessary files. Note this template is built against twitter-bootstrap v1.4.0,
so you'll need to checkout that tag.

Once the submodule files (modules/bootstrap and .gitmodules) have been committed to the repository,
other users can clone it in the normal fashion, but they must run:

    git submodule update

for the submodule files to be pulled in.

To update the version that is being used, you must run _git fetch/pull_ in the submodule
directory, then commit the version the submodule is using to the main project. For
more information, see:

[Understanding Git Submodules](http://speirs.org/blog/2009/5/11/understanding-git-submodules.html)

# MongoDB

This app uses MongoDB. Therefore, you will need to either have it installed locally, or use one of
the cloud providers and configure it in your props file. See config.MongoConfig for more info.

# Building

This app uses sbt 0.11.2. To build for the first time, run:

    bash\$ sbt
    > ~;container:start; container:reload /

That will start the app and automatically reload it whenever sources are modified. It will be running
on http://localhost:8080

# User  Model

This app implements the [Mongoauth Lift Module](https://github.com/eltimn/lift-mongoauth).
The registration and login implementation is based on
[research done by Google](http://sites.google.com/site/oauthgoog/UXFedLogin) a few years ago
and is similar to Amazon.com and Buy.com. It's different than what most people seem to expect,
but it can easily be changed to suit your needs since most of the code is part of your project.
