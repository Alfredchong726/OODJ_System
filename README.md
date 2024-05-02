## Github basic usage

First open git bash and use `cd` command to get into the target directory
```
cd /path/to/oodj_system
```

Stage the modified file/new file
```
git add .
```

Add a short description for the commit
```
git commit -am 'Short Description for this commit'
```

Push the lastest changes to specific branch
```
git push origin BRANCH_NAME
```

## Special case
For example, u created 5 files, but only 2 files u wanna add can specific the file(s) that wanna add
```
git add /paath/to/file1 /path/to/file2
```

To pull all the latest changes from github need to use `cd` command to get into the target directory, then run `git pull`
```
git pull
```
