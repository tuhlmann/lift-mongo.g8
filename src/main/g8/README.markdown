# Twitter Bootstrap

Bootstrap is included via a git submodule. When first checking out this project
you must run:

  git init
  git submodule add https://github.com/twitter/bootstrap.git src/main/javascript/js/bootstrap
  git submodule init
  git add src/main/javascript/js/bootstrap

This will pull in the necessary files.

To update the version that is being used, you must run @git fetch/pull@ in the submodule
directory, then commit the version the submodule is using to the main project. For
more information, see:

[Understanding Git Submodules](http://speirs.org/blog/2009/5/11/understanding-git-submodules.html)

# MongoDB

This app uses MongoDB. Therefore, you will need to either have it installed locally, or use one of the cloud providers and configure it in your props file. See config.MongoConfig for more info.

# Building

This app uses sbt 0.11. To build for the first time, run:

    bash\$ sbt
    > update
    > compile
    > container:start

It will be running on http://localhost:8080