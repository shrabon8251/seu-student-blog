// SEU Student Blog — mock data + shared interactions
const BLOGS = [
  {id:1,title:"How I Built My First Spring Boot Application",cat:"Programming",author:"Rahim Ahmed",dept:"CSE · 3rd Year",date:"Sep 21, 2026",read:"5 min read",img:"https://images.unsplash.com/photo-1517694712202-14dd9538aa97?w=800&q=80",excerpt:"A walkthrough of my first real backend project — what broke, what clicked, and what I'd do differently."},
  {id:2,title:"Understanding OOP Through Real Projects",cat:"Programming",author:"Nusrat Jahan",dept:"CSE · 2nd Year",date:"Sep 19, 2026",read:"6 min read",img:"https://images.unsplash.com/photo-1555066931-4365d14bab8c?w=800&q=80",excerpt:"Inheritance and polymorphism finally made sense once I stopped reading theory and started building."},
  {id:3,title:"A Beginner's Guide to MongoDB",cat:"Database",author:"Tanvir Hasan",dept:"CSE · 3rd Year",date:"Sep 17, 2026",read:"7 min read",img:"https://images.unsplash.com/photo-1544383835-bda2bc66a55d?w=800&q=80",excerpt:"Documents, collections and indexes — a practical primer for students coming from SQL."},
  {id:4,title:"What I Learned From Building a University Project",dept:"CSE · 4th Year",cat:"Student Life",author:"Farhana Akter",date:"Sep 15, 2026",read:"4 min read",img:"https://images.unsplash.com/photo-1522202176988-66273c2fd55f?w=800&q=80",excerpt:"Team projects teach you more about communication than code. Here's what stuck with me."},
  {id:5,title:"Introduction to Computer Networking",cat:"Networking",author:"Shamim Reza",dept:"CSE · 2nd Year",date:"Sep 13, 2026",read:"8 min read",img:"https://images.unsplash.com/photo-1544197150-b99a580bb7a8?w=800&q=80",excerpt:"OSI layers stopped being abstract once I mapped them to my own home network."},
  {id:6,title:"How Students Can Start Learning AI",cat:"Artificial Intelligence",author:"Mahin Chowdhury",dept:"CSE · 3rd Year",date:"Sep 11, 2026",read:"6 min read",img:"https://images.unsplash.com/photo-1555255707-c07966088b7b?w=800&q=80",excerpt:"You don't need a GPU cluster to start — here's the roadmap I wish I had in first year."},
  {id:7,title:"My Journey From C to Java",cat:"Programming",author:"Ishrat Zaman",dept:"CSE · 2nd Year",date:"Sep 9, 2026",read:"5 min read",img:"https://images.unsplash.com/photo-1517180102446-f3ece451e9d8?w=800&q=80",excerpt:"Switching languages mid-degree felt risky. It turned out to be the best decision I made."},
  {id:8,title:"Understanding REST APIs",cat:"Web Development",author:"Rafiul Islam",dept:"CSE · 3rd Year",date:"Sep 7, 2026",read:"6 min read",img:"https://images.unsplash.com/photo-1461749280684-dccba630e2f6?w=800&q=80",excerpt:"Verbs, status codes and statelessness — a clear mental model for building and consuming APIs."},
  {id:9,title:"Why Git Matters for Every CSE Student",cat:"Career",author:"Sabbir Hossain",dept:"CSE · 4th Year",date:"Sep 5, 2026",read:"4 min read",img:"https://images.unsplash.com/photo-1556075798-4825dfaaf498?w=800&q=80",excerpt:"Nobody taught us Git properly in class. Here's what I had to learn the hard way."},
  {id:10,title:"Building Better Projects as a University Student",cat:"Student Life",author:"Anika Tabassum",dept:"CSE · 3rd Year",date:"Sep 3, 2026",read:"5 min read",img:"https://images.unsplash.com/photo-1519389950473-47ba0277781c?w=800&q=80",excerpt:"Scope, documentation and version control — the unglamorous habits that make projects succeed."},
];

const CATEGORIES = [
  {name:"Programming",icon:"💻",count:24,desc:"Languages, patterns and development concepts."},
  {name:"Web Development",icon:"🌐",count:16,desc:"Frontend, backend and full-stack fundamentals."},
  {name:"Artificial Intelligence",icon:"🤖",count:12,desc:"Machine learning, deep learning and applied AI."},
  {name:"Networking",icon:"📡",count:9,desc:"Protocols, infrastructure and connectivity."},
  {name:"Cyber Security",icon:"🔒",count:8,desc:"Security fundamentals and safe practices."},
  {name:"Database",icon:"🗄️",count:11,desc:"SQL, NoSQL and data modeling."},
  {name:"Education",icon:"🎓",count:7,desc:"Study methods and academic experiences."},
  {name:"Career",icon:"💼",count:10,desc:"Internships, interviews and job prep."},
  {name:"Student Life",icon:"🏫",count:14,desc:"Campus life and personal growth."},
  {name:"Technology",icon:"⚡",count:13,desc:"Tools, trends and tech news for students."},
];

function initNav(){
  const toggle = document.querySelector(".nav-toggle");
  const links = document.querySelector(".nav-links");
  if(toggle && links){
    toggle.addEventListener("click",()=>links.classList.toggle("open"));
  }
}
document.addEventListener("DOMContentLoaded",initNav);

function cardHTML(b){
  return `<a href="blog-details.html?id=${b.id}" class="blog-card fade-in">
    <div class="thumb"><img src="${b.img}" alt="${b.title}" loading="lazy"></div>
    <div class="body">
      <span class="tag">${b.cat}</span>
      <h3>${b.title}</h3>
      <p>${b.excerpt}</p>
      <div class="meta"><span>${b.author}</span><span class="dot"></span><span>${b.date}</span></div>
      <div class="meta mt-8">${b.read}</div>
    </div>
  </a>`;
}
