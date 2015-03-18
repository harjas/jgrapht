git add .
git commit -m "starting new trigger build post changes"
git push origin master
curl http://cs498dm-23a.cs.illinois.edu:8080/git/notifyCommit?url=https://github.com/harjas/jgrapht
