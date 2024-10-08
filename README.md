## Github basic usage

First open git bash and use `cd` command to get into the target directory, then use `git clone` to clone the repository
```
cd /path/to/oodj_system

git clone https://github.com/Alfredchong726/OODJ_System.git
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

To pull all the latest changes from github need to use `cd` command to get into the target directory, then run `git pull`
```
git pull
```

## Special case
For example, u created 5 files, but only 2 files u wanna add can specific the file(s) that wanna add
```
git add /paath/to/file1 /path/to/file2
```

## Code Standard
`lib`: The external library that used in this project

`src/main/java/com/`: contain all the classes

`src/main/resources/com/css`: contain all the css file for decorating the GUI

`src/main/resources/com/example`: contain all the fxml files which represent the scene

`src/main/resources/com/textFiles`: contain all the text files that used in this project

`src/main/java/com/shared/SharedFunctions.java`: All the read file and write file functions should be in here


## Test Case
### Admin
- [X] Register Student
- [X] Register Lecturer
- [X] Amend Student Info
- [X] Amend Lecturer Info
- [X] Assign Lecturer to Project Manager
- [X] Unassign Project Manager to Lecturer
### Lecturer/Project Manager
- [x] View student
- [x] View presentation Requests
- [x] View second marker acceptance
- [x] Accept Presentation slot
- [x] Evaluate feedback
- [x] Dashboard
- [x] Allot assesment type to student
- [x] Assign supervisor and second marker to student
- [x] View status of report
### Strudent
- [x] Submit report
- [x] Request presentation slot
- [x] Check result / status of submission
