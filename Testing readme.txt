Description
===================
Here, I have taken following file for compression and decompression:
Word File-		Sample word file.doc 
Image File-		Sample image file.jpg
Audio File-		Sample music file.mp3
DLL File-		Sample dll file.dll
EXE File-		Sample exe file.exe


User have to select radio button for compression any of the following types:
Word File, Image File, Audio File, DLL File or Exe File. 

======================================================================================
1.	To Compress Files:
After selecting any radio button, user can click on compress button to compress files. 
After compressing file, Proper Message is displayed.

2.	To Decompress Files:
To decompress file, user have to select any radio button and click on decompress button. 
If File has already encoded then decompression will be done successfully, Proper message box will be displayed:

If File is not encoded earlier then no file is found for decompression. 
So Proper message will be displayed in message box.
=====================================================================================  

Testing:
------------
Test Scenario 1:(Check Whether File is compressed or not)
1. Select Any Radio Button from GUI(e.g Word File)
2. Click On Compress button
3. Verify That Proper message is displayed in message box
   e.g Word file is compressed.
4. Go To MP-Project/Inputes and verify that *.doc.lzw file is created.
==============================================================================================================
Test Scenario 2:(Check Whether File is decompressed or not)
1. Select Any Radio Button from GUI(e.g Word File)
2. Click On Decompress button
3. Verify That Proper message is displayed in message box
   e.g Word file is decompressed.
4. Go To MP-Project/Inputes and verify that *New.doc file is created. 
5. Verify that Newly created file and original file are same.
===============================================================================================================
TestScenario 3:(Check Whether Message is displayed or not)
1. Select Any Radio Button from GUI(e.g Image File) for which we didnot created compressed file.
2. Click On Decompress button
3. Verify That Proper message is displayed in message box
   e.g Compression is not done.Please perform compression for file.
================================================================================================================