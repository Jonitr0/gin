#delete old files
rm ../spatial4j/$projectnameforgin.GP_output_line.csv
rm ../spatial4j/$projectnameforgin.GP_output_transplant.csv
rm ../spatial4j/$projectnameforgin.GP_output_combined.csv
#build gin
gradle build
#build and test spatial4j
cd ../spatial4j
mvn compile
mvn test
#run profiler
projectnameforgin='spatial4j'; java -Dtinylog.level=trace -cp ../gin/build/gin.jar gin.util.Profiler -r 1 -mavenHome /usr/share/maven -n 20 -p $projectnameforgin -d . -o $projectnameforgin.Profiler_output.csv
#run empty test patcher
java -Dtinylog.level=trace -cp ../gin/build/gin.jar gin.util.EmptyPatchTester -J -p $projectnameforgin -d . -m $projectnameforgin.Profiler_output.csv -o $projectnameforgin.EmptyPatchTester_output.csv  -mavenHome /usr/share/maven
#define variables
numGens='2';
numIndivids='2'
#run gin line
editType='LINE'; generations=$numGens; individuals=$numIndivids; java -Dtinylog.level=trace -cp ../gin/build/gin.jar gin.util.GPRuntime -j -p $projectnameforgin -d . -m $projectnameforgin.Profiler_output.csv -o $projectnameforgin.GP_output_line.csv -mavenHome /usr/share/maven -editType $editType -gn $generations -in $individuals
#run gin transplant
editType='gin.edit.transplant.ReplaceWithTransplantLine'; generations=$numGens; individuals=$numIndivids; java -Dtinylog.level=trace -cp ../gin/build/gin.jar gin.util.GPRuntime -j -p $projectnameforgin -d . -m $projectnameforgin.Profiler_output.csv -o $projectnameforgin.GP_output_transplant.csv -mavenHome /usr/share/maven -editType $editType -gn $generations -in $individuals -ds ../gin/experiment/Geometry.java
#run gin combined
editType='COMBINED_TRANSPLANT_LINE'; generations=$numGens; individuals=$numIndivids; java -Dtinylog.level=trace -cp ../gin/build/gin.jar gin.util.GPRuntime -j -p $projectnameforgin -d . -m $projectnameforgin.Profiler_output.csv -o $projectnameforgin.GP_output_combined.csv -mavenHome /usr/share/maven -editType $editType -gn $generations -in $individuals -ds ../gin/experiment/Geometry.java
