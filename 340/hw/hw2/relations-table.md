<table border="0" cellpadding="10">
<tbody><tr>
  <td>  
  </td> <!-- end of left column -->
  <td>  <!-- right column is the table to be filled in -->
    <table border="1" cellpadding="4">
    <tbody><tr>
      <th>&nbsp;</th>
      <th>Domain</th>
      <th>Key</th>
      <th>Entity<br>Integrity</th>
      <th>Referential<br>Integrity</th>
    </tr>
    <tr><td>(a)</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
               <td>X</td></tr>
    <tr><td>(b)</td><td>X</td><td>&nbsp;</td><td>&nbsp;</td>
               <td>&nbsp;</td></tr>
    <tr><td>(c)</td><td>&nbsp;</td><td>&nbsp;</td><td>X</td>
               <td>&nbsp;</td></tr>
    <tr><td>(d)</td><td>X</td><td>X</td><td>&nbsp;</td>
               <td>&nbsp;</td></tr>
    <tr><td>(e)</td><td>&nbsp;</td><td>&nbsp;</td><td>X</td>
               <td>&nbsp;</td></tr>
    <tr><td>(f)</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
               <td>&nbsp;</td></tr>
    <tr><td>(g)</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
               <td>&nbsp;</td></tr>
    </tbody></table>
  </td> <!-- end of right column -->
</tr>
</tbody></table>

f)
Deletes 
<'Jennifer', 'S', 'Wallace', '987654321', '1941-08-20', '291 Berry, Bellaire, TX', 'F', '43000', '888665555', '4'> of Employee
<'Administration', '4', '987654321', '1995-01-01'> of Department.
<'4', 'Stafford'> of Dept Locations
<'987654321', '30', '20.0'>, <'987654321', '20', '15.0'> of Works_On
<'987654321', 'Abner', 'M', '1942-02-28', 'Spouse'> of Dependent table. 

g)
Deletes
<'Headquarters', '1', '888664444', '1981-06-19'> from Department
<'1', 'Houston'> from Dept_Locations. 
<'Reorganization', '20', 'Houston', '1'> from Project. 
<'James', 'E', 'Borg', '888665555', '1937-11-10', '450 Stone, Houston, TX', 'M', '55000', NULL, '1'> of Employee

Primary Keys are Italic.

- **TV_Series**(<span style="text-decoration:underline">SeriesTitle<sup>1</sup>,CreatorName<sup>1</sup></span> TV_Series_Title, TV_Series_CreatorName)

- **Actor**(<span style="text-decoration:underline">StageName<sup>1</sup>, DateOfBirth<sup>1</sup>,</span> RealName<sup>1</sup>, CharacterName<sup>4</sup>, EpisodeNum<sup>4</sup>, SeasonNum<sup>4</sup>, EpisodeTitle<sup>4</sup>)

- **Episode**(<span style="text-decoration: underline">*SeasonNum*<sup>2</sup>, *EpisodeNum*<sup>2</sup>,</span> EpisodeTitle<sup>2</sup>, *SeriesTitle*<sup>4</sup>, *SeriesCreatorName*<sup>4</sup>)

- **Character**(<span style="text-decoration:underline">*Name*<sup>2</sup>, *SeasonNum*<sup>4</sup>, *EpisodeNum*<sup>4</sup></span>,EpisodeTitle<sup>4</sup>, EpisodeSeasonNum<sup>7</sup>, ActorStageName<sup>7</sup>, ActorDateOfBirth<sup>7</sup>, Duration<sup>7</sup>)

- **Showing**(<span style="text-decoration:underline">Date, Time, Network, EpisodeNum, SeasonNum</span>)<sup>6</sup>

