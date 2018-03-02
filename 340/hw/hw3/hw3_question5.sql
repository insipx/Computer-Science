PROJECT Dname, Dlocation
FROM (JOIN Department WITH (PROJECT Dnumber AS DLnumber, Dlocation FROM Dept_Locations) WHERE Dnumber = DLnumber)


/* Get All Managers */
JOIN Employee WITH Department WHERE Dno = Mgr_Ssn;


/* Get Departments and their locations */
(JOIN Department WITH (PROJECT Dnumber AS DLnumber, Dlocation FROM Dept_Locations) WHERE Dnumber = DLnumber)

/* Get Manager SSN */







FROM JOIN Employee As e
  WITH (RESTRICT
       ((PROJECT Ssn FROM Employee) -
       (PROJECT Essn AS Ssn FROM (JOIN (RESTRICT Project WHERE Plocation = 'Wilkes-Barre') WITH Works_On WHERE Pno = Pnumber)))
        WHERE Plocation = 'Scranton') AS f
  WHERE e.Ssn = f.Ssn;



PROJECT Dname, Dlocation FROM
JOIN (PROJECT Dnumber AS DLnumber, Dlocation FROM Dept_Locations) WITH  
    (RESTRICT 
      (JOIN (PROJECT Ssn, Dno FROM Employee) WITH (PROJECT Dnumber, Mgr_Ssn FROM Department) WHERE Mgr_Ssn = Ssn) 
    WHERE Dno != Dnumber)
  WHERE DLnumber = Dnumber;




  PROJECT Fname, Lname
  FROM JOIN Employee AS e
    WITH (RESTRICT
      ((PROJECT Ssn FROM Employee) -
      (PROJECT Essn as Ssn FROM (JOIN (RESTRICT Project WHERE Plocation = 'Wilkes-Barre') WITH Works_On WHERE Pno = Pnumber)))
    WHERE Plocation = 'Scranton') as f
  WHERE e.Ssn = f.Ssn






