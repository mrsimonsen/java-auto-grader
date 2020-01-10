#data maker for java
import csv, shelve, datetime
#support classes
class Assignment(object):
    '''an assignment with a folder, file name, and due date'''

    def __init__(self,folder,file,due,test):
        self.folder = folder
        self.file = file
        self.due = due
        self.test = test

    def __str__(self):
        rep = f"{self.folder}\\{self.file}\n{self.due}\n{self.test}"
        return rep
class Student(object):
    '''a student with name, weber username, and github username'''

    def __init__(self, name, period, github):
        self.name = name
        self.github = github
        self.period = period
        self.assignment = Assignment('error','',datetime.datetime.today(),'')
        self.score = 0
        self.late = True
        self.submit = None

    def __str__(self):
        rep = f"{self.name}, Period {self.period}\n{self.github}\n--Current Assignment--\n{self.assignment.folder}\\{self.assignment.file}\n{self.score} points\nSubmitted:{self.submit}\nLate = {self.late}"
        return rep

    def set_grade(self, assign_obj, score):
        self.assignment = assign_obj
        self.score = score
        if assign_obj.due > self.submit:
            self.late = False
def main():
    #create shelve file, overwrite old file if exists
    data = shelve.open('grading_data','n')

    #assignment details
    assignments = ('00','01','02','03','04','05','06','07','08','09','10','11','12','13','14','15','16','17','18','19','20','21','1')
    file_names = {"1":("Hi.java",),
    "00":("HelloWorld.java",),
    "01":("BasicInput.java",),
    "02":("PaintEstimator.java",),
    "03":("TextMsgAbbreviation.java",),
    "04":("TextMsgDecoder.java",),
    "05":("TextMsgExpander.java",),
    "06":("DrawRightTriangle.java",),
    "07":("DrawHalfArrow.java",),
    "08":("PeopleWeights.java",),
    "09":("PlayerRoster.java",),
    "10":("CoinFlipper.java",),
    "11":("ReverseMessage.java",),
    "12":("DiceStats.java",),
    "13":("TextAnalyzer.java",),
    "14":("AuthoringAssistant.java",),
    "15":("ShoppingCartPrinter.java","ItemToPurchase.java"),
    "16":("ShoppingCartManager.java","ItemToPurchase.java,ShoppingCart.java"),
    "17":("BinaryConverter.java",),
    "18":("ParseStrings.java",),
    "19":("DataVisualizer.java",),
    "20":("CaesarCipher.java","ReadWrite.java","Support.java"),
    "21":("Yahtzee.java",)}
    folders = {"1":"test1",
    "00":"00HelloWorld",
    "01":"01BasicInput",
    "02":"02PaintEstimator",
    "03":"03TextMsgAbbreviation",
    "04":"04TextMsgDecoder",
    "05":"05TextMsgExpander",
    "06":"06DrawRightTriangle",
    "07":"07DrawHalfArrow",
    "08":"08PeopleWeights",
    "09":"09PlayerRoster",
    "10":"10CoinFlipper",
    "11":"11ReverseMessage",
    "12":"12DiceStats",
    "13":"13TextAnalyzer",
    "14":"14AuthoringAssistant",
    "15":"15ShoppingCartPrinter",
    "16":"16ShoppingCartManager",
    "17":"17BinaryConverter",
    "18":"18ParseStrings",
    "19":"19DataVisualizer",
    "20":"20CaesarCipher",
    "21":"21Yahtzee"}
    due_dates = {'1':datetime.datetime.today(),
    '00':datetime.datetime.today(),
    '01':datetime.datetime.today(),
    '02':datetime.datetime.today(),
    '03':datetime.datetime.today(),
    '04':datetime.datetime.today(),
    '05':datetime.datetime.today(),
    '06':datetime.datetime.today(),
    '07':datetime.datetime.today(),
    '08':datetime.datetime.today(),
    '09':datetime.datetime.today(),
    '10':datetime.datetime.today(),
    '11':datetime.datetime.today(),
    '12':datetime.datetime.today(),
    '13':datetime.datetime.today(),
    '14':datetime.datetime.today(),
    '15':datetime.datetime.today(),
    '16':datetime.datetime.today(),
    '17':datetime.datetime.today(),
    '18':datetime.datetime.today(),
    '19':datetime.datetime.today(),
    '20':datetime.datetime.today(),
    '21':datetime.datetime.today()}
    tests = {'1':'test_hi.java',
    '00':'Test00.java',
    '01':'Test01.java',
    '02':'Test02.java',
    '03':'Test03.java',
    '04':'Test04.java',
    '05':'Test05.java',
    '06':'Test06.java',
    '07':'Test07.java',
    '08':'Test08.java',
    '09':'Test09.java',
    '10':'Test10.java',
    '11':'Test11.java',
    '12':'Test12.java',
    '13':'Test13.java',
    '14':'Test14.java',
    '15':'Test15.java',
    '16':'Test16.java',
    '17':'Test17.java',
    '18':'Test18.java',
    '19':'Test19.java',
    '20':'Test20.java',
    '21':'Test21.java',}

    for i in assignments:
        data[i]=Assignment(folders[i],file_names[i],due_dates[i],tests[i])

    #student details
    names = {}
    students = []
    with open('usernames - Form Responses 1.csv','r',newline='') as f:
        #format = time,first,last,git,weber
        raw = csv.reader(f,delimiter=',',quotechar='"')
        for row in raw:
            if row[1] == "What is your First Name?":
                continue#skip the first row/header
            students.append(Student(f"{row[2]}, {row[1]}",int(row[3]),row[4]))
    data['students'] = students
    #save all the things
    data.close()
